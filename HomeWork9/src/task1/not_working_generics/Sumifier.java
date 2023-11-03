package task1.not_working_generics;

import java.util.concurrent.Callable;

public class Sumifier<T extends Number> implements Callable<T> {
    private T[] array;
    public Sumifier(T[] array){
        this.array = array;
    }
    @Override
    public T call() throws Exception {
        Double sum = 0d;
        for (int i=0; i<array.length; i++){
            sum += array[i].doubleValue();
        }
        return (T) sum;
    }
}
