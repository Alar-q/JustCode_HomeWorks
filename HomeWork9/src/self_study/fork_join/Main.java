package self_study.fork_join;

import java.util.concurrent.ForkJoinPool;

public class Main {
    // pool will use 2 processor cores
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(2);

    public static void main(String[] args) {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        // RecursiveAction and RecursiveTask<V>
        // compute()
        CustomRecursiveAction task = new CustomRecursiveAction("Example workload");
        commonPool.submit(task).join();
    }
}
