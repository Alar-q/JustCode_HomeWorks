package task1.not_working_generics;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * При старте приложения запускаются три потока.
 * Первый поток заполняет массив случайными числами.
 * Два других потока ожидают заполнения.
 * Когда массив заполнен оба потока запускаются.
 * Первый поток находит сумму элементов массива,
 * второй поток среднеарифметическое значение в массиве.
 * Полученный массив, сумма и среднеарифметическое возвращаются в метод main,
 * где должны быть отображены.
 *
 * Что входит в тему многопоточности
 * Pure Threads
 * ExecutorService
 * ForkJoinFramework
 * CountDownLatch
 * Semaphore
 * CyclicBarrier
 * */
public class Main {
    public static void main(String[] args) {
        try {
            Integer[] array = new Integer[5];

            ExecutorService executorService = Executors.newFixedThreadPool(3);

            Future<Integer[]> futureFilled = executorService.submit(new Filler(array));
//            Future<int[]> futureFilled = executorService.submit(new Filler(array));

            array = futureFilled.get();
            System.out.println(Arrays.toString(array));

//            Future<Integer> futureSum = executorService.submit(new Sumifier<Integer>(array));
//            Future<Double> futureMean = executorService.submit(new MeanFinder<Integer>(array));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
