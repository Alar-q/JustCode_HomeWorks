package task3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static String directoryPath, searchWord, bannedWordsFilePath, mergedFilePath;
    public static List<String> bannedWords;
    public static CountDownLatch latch;
    public static AtomicInteger fileCount, dirCount;

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir")); // Путь к файлу Main или C:\git\JustCode_HomeWorks\HomeWork10

        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Введите путь к директории для поиска слова: ");
            directoryPath = scanner.nextLine(); // "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/assets"
//            directoryPath = "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/assets";

            System.out.print("Введите слово для поиска: ");
            searchWord = scanner.nextLine();// "Пользователь"
//            searchWord = "Пользователь";

            System.out.print("Введите путь к файлу с запрещенными словами: ");
            bannedWordsFilePath = scanner.nextLine(); // "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/assets/banned.txt"
//            bannedWordsFilePath = "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/assets/banned.txt";

            System.out.print("Введите путь к файлу куда записать результат: ");
            mergedFilePath = scanner.nextLine(); // "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/merged.txt"
//            mergedFilePath = "C:/git/JustCode_HomeWorks/HomeWork10/src/task3/merged.txt";

        } catch (Exception e){
            System.out.println("Something went wrong!");
            e.printStackTrace();
            return;
        }

        fileCount = new AtomicInteger();
        dirCount = new AtomicInteger();

        latch = new CountDownLatch(1);


        bannedWords = new ArrayList<>();

        // Чтение списка запрещенных слов
        try (Stream<String> stream = Files.lines(Paths.get(bannedWordsFilePath), StandardCharsets.UTF_8)) {
            bannedWords.addAll(stream.collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла с запрещенными словами.");
            e.printStackTrace();
            return;
        }

        // Поток для поиска и объединения файлов
        SearchWordThread searchWordThread = new SearchWordThread();
        // Поток для удаления запрещенных слов
        WordReplacerThread wordReplacerThread = new WordReplacerThread();

        searchWordThread.start();
        wordReplacerThread.start();

        try {
            wordReplacerThread.join();
            wordReplacerThread.join(); // Ждем завершения потоков
            System.out.println("Проверено файлов: " + fileCount + ", директорий: " + dirCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
