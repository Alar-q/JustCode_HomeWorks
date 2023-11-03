package self_study.executor_service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * References:
    A Guide to the Java ExecutorService. https://www.baeldung.com/java-executor-service-tutorial.

 * Instantiating ExecutorService

     ExecutorService executor = Executors.newFixedThreadPool(10);

         ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>()
         );

 * Assigning Tasks to the ExecutorService

     void execute() - doesn’t give any possibility to get the result of a task’s execution or to check the task’s status
        executorService.execute(runnableTask);

     Future<?> submit() - submits a Callable or a Runnable task to an ExecutorService and returns a result of type Future
        Future<String> future = executorService.submit(callableTask);

     <?> invokeAny()
        String result = executorService.invokeAny(callableTasks);

     List<Future<String>> invokeAll()
        List<Future<String>> futures = executorService.invokeAll(callableTasks);


 * Future

     future.get(200, TimeUnit.MILLISECONDS);
     boolean canceled = future.cancel(true);
     boolean isCancelled = future.isCancelled();

 * The ScheduledExecutorService Interface

    To schedule a single task’s execution after a fixed delay
        Future<String> resultFuture = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);

    executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);

    executorService.scheduleWithFixedDelay(task, 100, 150, TimeUnit.MILLISECONDS);
 * */
public class Main {
    private static Runnable runnableTask = () -> {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static Callable<String> callableTask = () -> {
        TimeUnit.MILLISECONDS.sleep(3000);
        return "Task's execution result " + Math.random() + " name: " + Thread.currentThread().getName();
    };

    public static void main(String[] args)  {
        // Factory Methods of the Executors Class
        ExecutorService executorService = Executors.newWorkStealingPool();
        // Also newCachedThreadPool, newWorkStealingPool

        try {
            // execute(Runnable), submit(Callable), invokeAny(List), invokeAll(List)
            Future<String> res = executorService.submit(callableTask);
            System.out.println("res: " + res.get(4000, TimeUnit.MILLISECONDS));
            System.out.println("kek"); // awaiting
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        List<Future<String>> futures;

        try {
            futures = executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown(); // больше не принимает новых задач
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        futures.stream().forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Future<String> res = executorService.submit(callableTask);
            System.out.println("res: " + res.get(4000, TimeUnit.MILLISECONDS));
            System.out.println("kek");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown(ExecutorService executorService){
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
