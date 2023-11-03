package task1;

import java.util.concurrent.Callable;

public class Sumifier implements Callable<Double> {
    private double[] array;
    public Sumifier(double[] array){
        this.array = array;
    }
    @Override
    public Double call() throws Exception {
        double sum = 0d;
        for (int i=0; i<array.length; i++){
            sum += array[i];
        }

        System.out.println("Sumifier: " + sum);

        return sum;
    }
}
