package self_study.runnable_vs_callable;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;

public class FactorialTask implements Callable<Integer> {
    int number;

    // standard constructors
    public FactorialTask(int number){
        this.number = number;
    }

    public Integer call() throws InvalidParameterException {
        int fact = 1;
        // ...
        for(int count = number; count > 1; count--) {
            fact = fact * count;
        }

        return fact;
    }
}
