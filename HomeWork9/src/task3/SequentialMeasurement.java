package task3;

import utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class SequentialMeasurement {
    public static void main(String[] args) throws Exception {
        // Создаем сервис для выполнения потоков
        ExecutorService service = Executors.newFixedThreadPool(4);

        // Создаем Callable для первого потока, который создает коллекции
        Callable<List[]> task1 = () -> {
            List<Integer> integers = new ArrayList<>();
            List<Double> doubles = new ArrayList<>();
            for(int i=0; i<Main.N; i++){
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
        System.out.println("Integers: " + integers);
        System.out.println("Doubles: " + doubles);

        long startTime = System.nanoTime();

        // Запускаем остальные потоки
        Long futureIntSum = integers.stream().mapToLong(Long::valueOf).sum();
        Double futureDoubleSum = doubles.stream().mapToDouble(Double::doubleValue).sum();
        Integer futureUniqueCount = ((Supplier<Integer>)() -> {
            Set<Number> uniqueElements = new HashSet<>(); // пользуемся Set-ом для нахождения уникальных значений
            uniqueElements.addAll(integers);
            uniqueElements.addAll(doubles);
            return uniqueElements.size();
        }).get();

        System.out.println("Sum of Integers: " + futureIntSum);
        System.out.println("Sum of Doubles: " + futureDoubleSum);
        System.out.println("Unique Elements: " + futureUniqueCount);

        System.out.println("Обработка массивов заняла секунд:" + (System.nanoTime() - startTime) / 1000000000d);
        // Завершаем сервис после выполнения всех задач
        Utils.shutdown(service);
    }
}
