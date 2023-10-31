package part1;

import java.util.function.Function;

public class MaxMin {
    private interface Fun<T, V> {
        V apply(T t);
    }
    public static void main(String[] args) {
        /** Lambda */
        Fun<int[], Integer> max = numbers -> {
            int maxNum = numbers[0];
            for(int num : numbers) {
                maxNum = Math.max(maxNum, num);
            }
            return maxNum;
        };
        System.out.println(max.apply(new int[]{1, 2, 3, 4})); // Output: 4

        /** Anonymous class */
        Fun<int[], Integer> min = new Fun<int[], Integer>() {
            @Override
            public Integer apply(int[] numbers) {
                int minNum = numbers[0];
                for(int num : numbers) {
                    minNum = Math.min(minNum, num);
                }
                return minNum;
            }
        };

        System.out.println(min.apply(new int[]{1, 2, 3, 4})); // Output: 1
    }
}
