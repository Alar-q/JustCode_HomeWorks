package part1;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaAsArg {
   /* record Point<T>(T x, T y){}

    public static void main(String[] args) {
        Float[] array = {1.1f, 2.1f, 3.1f, 4.1f, 5.1f};

        Predicate<Float> isPositive = number -> number > 0;
        Predicate<Float> isNegative = number -> number < 0;

        *//** Functions that return functions *//*
        Function<Float, Predicate<Float>> isEqualTo = threshold ->
                (Predicate<Float>) number -> number == threshold;

        Function<Point<Float>, Predicate<Float>> isOutOfRange = point -> {
            return (Predicate<Float>) (number) -> number < point.x() || number > point.y();
        };

        System.out.println(sum(array, isEqualTo.apply(3f)));
        System.out.println(sum(array, isPositive));
        System.out.println(sum(array, isNegative));
        System.out.println(sum(array, isOutOfRange.apply(new Point<>(1f, 4f))));
    }

    public static <T extends Number> Number sum(T[] array, Predicate<T> condition) {
        Stream<T> stream = Arrays.stream(array).filter(condition);
        return stream.mapToDouble(Number::doubleValue).sum();
    }*/

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};

        Predicate<Integer> isEqualToThree = number -> number == 3;
        Predicate<Integer> isPositive = number -> number > 0;
        Predicate<Integer> isOutOfRange = number -> number < 2 || number > 4;

        System.out.println(sum(array, isEqualToThree)); // Output: 3
        System.out.println(sum(array, isPositive)); // Output: 15
        System.out.println(sum(array, isOutOfRange)); // Output: 6
    }

    public static int sum(Integer[] array, Predicate<Integer> condition) {
        return Arrays.stream(array)
                .filter(condition)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
