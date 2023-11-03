package task3;

import utils.Utils;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Main {

    public static int N = 10_000_000;
    public static void main(String[] args) throws Exception {
        // Создаем сервис для выполнения потоков
        ExecutorService service = Executors.newFixedThreadPool(4);

        // Создаем Callable для первого потока, который создает коллекции
        Callable<List[]> task1 = () -> {
            List<Integer> integers = new ArrayList<>();
            List<Double> doubles = new ArrayList<>();
            for(int i=0; i<N; i++){
                integers.add(i);
                doubles.add(i + Math.random());
            }
            return new List[]{integers, doubles};
        };

        // Запускаем первый поток
        Future<List[]> futureLists = service.submit(task1);

        // Получаем результаты из первого потока
        List[] twoLists = futureLists.get();
        List<Integer> integers = twoLists[0];
        List<Double> doubles = twoLists[1];

        // Получаем и выводим результаты
//        System.out.println("Integers: " + integers);
//        System.out.println("Doubles: " + doubles);

        // Создаем Callable для второго, третьего и четвертого потоков
        // Используем Long, при суммировании int значений может получиться результат выходящий за рамки int
        Callable<Long> sumIntegersTask = () -> {
            System.out.println("sumIntegersTask: " + Thread.currentThread().getName());
            return integers.stream().mapToLong(Long::valueOf).sum();
        };
        Callable<Double> sumDoublesTask = () -> {
            System.out.println("sumDoublesTask: " + Thread.currentThread().getName());
            return doubles.stream().mapToDouble(Double::doubleValue).sum();
        };
        Callable<Integer> uniqueCountTask = () -> {
            System.out.println("uniqueCountTask: " + Thread.currentThread().getName());
            Set<Number> uniqueElements = new HashSet<>(); // пользуемся Set-ом для нахождения уникальных значений
            uniqueElements.addAll(integers);
            uniqueElements.addAll(doubles);
            return uniqueElements.size();
        };

        long startTime = System.nanoTime();

        // Запускаем остальные потоки
        Future<Long> futureIntSum = service.submit(sumIntegersTask);
        Future<Double> futureDoubleSum = service.submit(sumDoublesTask);
        Future<Integer> futureUniqueCount = service.submit(uniqueCountTask);

        System.out.println("Sum of Integers: " + futureIntSum.get());
        System.out.println("Sum of Doubles: " + futureDoubleSum.get());
        System.out.println("Unique Elements: " + futureUniqueCount.get());

        System.out.println("Обработка массивов заняла секунд:" + (System.nanoTime() - startTime) / 1000000000d);
        // Завершаем сервис после выполнения всех задач
        Utils.shutdown(service);
    }

}
