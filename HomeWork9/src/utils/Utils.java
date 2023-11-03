package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {
    /** Вечно ожидаем завершения */
    public static void shutdown(ExecutorService executorService){
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
