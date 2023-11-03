package task1;

import java.util.concurrent.Callable;

public class MeanFinder implements Callable<Double> {
    private double[] array;
    public MeanFinder(double[] array){
        this.array = array;
    }
    @Override
    public Double call() throws Exception {
        double sum = 0d;
        for (int i=0; i<array.length; i++){
            sum += array[i];
        }
        double mean = sum / array.length;

        System.out.println("MeanFinder: " + mean);

        return mean;
    }
}
