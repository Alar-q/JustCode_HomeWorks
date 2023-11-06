package task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        scanner.close();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(1);

        // Поток для записи случайных чисел в файл
        executor.submit(() -> {
            try {
                List<String> numbers = new Random()
                        .ints(100, 1, 101)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.toList());
//                Files.write(Paths.get(path), numbers);
                write(path, numbers);

            } catch (IOException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        // Поток для нахождения простых чисел
        executor.submit(() -> {
            try {
                latch.await();
//                List<String> primes = Files.readAllLines(Paths.get(path))
                List<String> primes = read(path)
                        .stream()
                        .map(Integer::parseInt)
                        .filter(Main::isPrime)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
//                Files.write(Paths.get(path + "_primes"), primes);
                write(path + "_primes", primes);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Поток для вычисления факториалов
        executor.submit(() -> {
            try {
                latch.await();
//                List<String> factorials = Files.readAllLines(Paths.get(path))
                List<String> factorials = read(path)
                        .stream()
                        .map(Integer::parseInt)
                        .map(number -> number + "!=" + factorial(BigInteger.valueOf(number)).toString())
                        .collect(Collectors.toList());
                Files.write(Paths.get(path + "_factorials"), factorials);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Операции выполнены. Проверьте файлы.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    public static BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.TWO; i.compareTo(number) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

    public static List<String> read(String path) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1028);
        Charset charset = StandardCharsets.UTF_8;

        StringBuilder stringBuilder = new StringBuilder();

        while(fileChannel.read(byteBuffer) != -1){
            byteBuffer.flip(); // Prepare the buffer for reading
            /*
            // Можно ложить либо побайтово, либо через Charset
            while (byteBuffer.hasRemaining()) {
                stringBuilder.append((char) byteBuffer.get());
            }*/
            stringBuilder.append(charset.decode(byteBuffer));

            byteBuffer.clear(); // Prepare the buffer for writing
        }

        randomAccessFile.close();
        fileChannel.close();

        // Split the string into lines
        return Arrays.stream(stringBuilder
                .toString()
                .split("\\r?\\n")) // The regex \\r?\\n is used to handle both Unix (\n) and Windows (\r\n) line endings
                .toList();
    }
    public static void write(String path, Iterable<String> array) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        Charset charset = StandardCharsets.UTF_8;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1028);

        for(String number: array){
            byteBuffer.put(charset.encode(number + System.lineSeparator()));
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();

//            Or we can do super easy
//            fileChannel.write(charset.encode(number));
        }

        randomAccessFile.close();
        fileChannel.close();
    }

}