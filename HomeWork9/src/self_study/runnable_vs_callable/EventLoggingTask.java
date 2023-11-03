package self_study.runnable_vs_callable;


public class EventLoggingTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Log");
    }
}
