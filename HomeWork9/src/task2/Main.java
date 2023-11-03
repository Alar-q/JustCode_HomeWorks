package task2;

import utils.Utils;
/**
 * При инициализации программы создаются пять(?) потоков.
 * Первый поток генерирует список из случайных строк.
 * Три других(?) потока ожидают, пока список строк будет полностью сгенерирован.
 * Как только список создан, все три потока запускаются.
 * Первый поток находит строку с наибольшей длиной,
 * второй поток находит строку с наименьшей длиной,
 * а третий поток считает общее количество символов во всех строках.
 * Полученный список строк, самая длинная строка, с
 * амая короткая строка и общее количество символов возвращаются в метод main,
 * где должны быть выведены на экран.
 * */

import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Создаем пул из 5-ти потоков
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Поток для генерации списка строк
        Future<List<String>> listFuture = executorService.submit(new StringGenerator(10));

        // Потоки для анализа списка строк
        Future<String> longestStringFuture = executorService.submit(new StringAnalyzer<>(listFuture, StringAnalyzer.Type.LONGEST));
        Future<String> shortestStringFuture = executorService.submit(new StringAnalyzer<>(listFuture, StringAnalyzer.Type.SHORTEST));
        Future<Integer> totalLengthFuture = executorService.submit(new StringAnalyzer<>(listFuture, StringAnalyzer.Type.TOTAL_LENGTH));

        // Ожидаем завершения всех задач
        Utils.shutdown(executorService);

        // Получаем и выводим результаты
        List<String> strings = listFuture.get();
        String longestString = longestStringFuture.get();
        String shortestString = shortestStringFuture.get();
        int totalLength = totalLengthFuture.get();

        System.out.println(String.format("Strings(%d): %s", strings.size(), strings.toString()));
        System.out.println(String.format("Longest String(%d): %s", longestString.length(), longestString));
        System.out.println(String.format("Shortest String(%d): %s", shortestString.length(), shortestString));
        System.out.println(String.format("Total Length of characters: %d", totalLength));
    }
}
