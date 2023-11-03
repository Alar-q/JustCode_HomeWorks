package task1.not_working_generics;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Filler<T extends Number> implements Callable<T[]> {
    private T[] array;
    public Filler(T[] array){
        // Создаём копию, чтобы не менять оригинал
        this.array = Arrays.copyOf(array, array.length);
//        this.array = array;
    }

    @Override
    public T[] call() throws Exception {
        for(int i=0; i<array.length; i++){
            Double number = Math.random() *  100000d;
            array[i] = (T) number;
        }
        return array;
    }

    /** Проверил работу метода Arrays.copyOf() and Arrays.copyOfRange() */
    private void testCopyMethod(){
        int[] array = new int[5];
        for(int i=0; i<array.length; i++){
            array[i] = i;
        }

        int[] array_copy = Arrays.copyOf(array, array.length);
//        int[] array_copy = Arrays.copyOfRange(array, 0, array.length);
        array_copy[0] = 1000;

        System.out.println("array: " + Arrays.toString(array));
        System.out.println("array_copy: " + Arrays.toString(array_copy));
    }
}