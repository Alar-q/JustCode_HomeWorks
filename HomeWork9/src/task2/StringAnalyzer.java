package task2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StringAnalyzer<T> implements Callable<T> {
    enum Type {
        LONGEST,
        SHORTEST,
        TOTAL_LENGTH
    }

    private final Future<List<String>> listFuture;
    private final Type type;

    StringAnalyzer(Future<List<String>> listFuture, Type type) {
        this.listFuture = listFuture;
        this.type = type;
    }

    @Override
    public T call() throws ExecutionException, InterruptedException {
        List<String> strings = listFuture.get();
        switch (type) {
            case LONGEST:
                return (T) Collections.max(strings, Comparator.comparing(String::length));
            case SHORTEST:
                return (T) Collections.min(strings, Comparator.comparing(String::length));
            case TOTAL_LENGTH:
                return (T) Integer.valueOf(strings.stream().mapToInt(String::length).sum());
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}