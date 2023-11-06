package task2;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к существующей директории: ");
        String sourcePath = scanner.nextLine();
        System.out.print("Введите путь к новой директории: ");
        String targetPath = scanner.nextLine();
        scanner.close();

        // Проверяем, существует ли исходная директория
        if (!Files.isDirectory(Paths.get(sourcePath))) {
            System.out.println("Исходная директория не существует.");
            return;
        }

        // Создаем целевую директорию, если она не существует
        try {
            Files.createDirectories(Paths.get(targetPath));
        } catch (IOException e) {
            System.out.println("Не удалось создать целевую директорию.");
            e.printStackTrace();
            return;
        }

        // Статистика выполнения
        AtomicInteger fileCount = new AtomicInteger();
        AtomicInteger dirCount = new AtomicInteger();

        // Поток для копирования
        Thread copyThread = new Thread(() -> {
            try {
                Files.walk(Paths.get(sourcePath))
                        .forEach(source -> {
                            System.out.println(targetPath + "+" + source.toString().substring(sourcePath.length()));
                            Path destination = Paths.get(targetPath, source.toString().substring(sourcePath.length()));
                            try {
                                if (Files.isDirectory(source)) {
                                    Files.createDirectories(destination);
                                    dirCount.incrementAndGet();
                                } else { // file
                                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                                    fileCount.incrementAndGet();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            copyThread.start();
            copyThread.join(); // Ждем завершения потока
            System.out.println("Копирование завершено. Скопировано файлов: " + fileCount + ", директорий: " + dirCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
