package task1;

import utils.Utils;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


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
            double[] array = new double[5];
            System.out.println(Arrays.toString(array));

            ExecutorService executorService = Executors.newFixedThreadPool(3);

            Future<double[]> futureFilled = executorService.submit(new Filler(array, -3d, 9));

            array = futureFilled.get();

            Future<Double> futureSum = executorService.submit(new Sumifier(array));
            Future<Double> futureMean = executorService.submit(new MeanFinder(array));

            // await shutdown
            Utils.shutdown(executorService);

            System.out.println("\nAfter the executor shut down");
            System.out.println(Arrays.toString(array));
            System.out.println(futureSum.get());
            System.out.println(futureMean.get());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
