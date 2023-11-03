package self_study.runnable_vs_callable;

import java.util.concurrent.*;

/**
 public interface Runnable {
    public void run();
 }

 public interface Callable<V> {
    V call() throws Exception;
 }

 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Runnable example");
        new Main().executeTask();
        System.out.println("\nCallable example");
        new Main().whenTaskSubmitted_ThenFutureResultObtained();
    }
    public void executeTask() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new EventLoggingTask());
        executorService.shutdown();
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void whenTaskSubmitted_ThenFutureResultObtained(){
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            FactorialTask task = new FactorialTask(5);
            Future<Integer> future = executorService.submit(task);

            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            if (!future.isDone())
                System.out.println("Callable is done, even if there was Exception");

            System.out.println(future.get());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
