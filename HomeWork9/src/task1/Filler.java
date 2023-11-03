package task1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

public class Filler implements Callable<double[]> {
    private double[] array;
    private double from, to;

    public Filler(double[] array, double from, double to){
        // Создаём копию, чтобы не менять оригинал
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    public double[] call() throws Exception {
        for(int i=0; i<array.length; i++){
            array[i] = (Math.random() *  (to - from)) + from;
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

}