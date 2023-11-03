package task1.not_working_generics;

import java.util.concurrent.Callable;

public class MeanFinder<T extends Number> implements Callable<Double> {
    private T[] array;
    public MeanFinder(T[] array){
        this.array = array;
    }
    @Override
    public Double call() throws Exception {
        Double sum = 0d;
        for (int i=0; i<array.length; i++){
            sum += array[i].doubleValue();
        }
        sum /= array.length;
        return sum;
    }
}
