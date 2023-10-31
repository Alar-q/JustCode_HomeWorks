package part1;

import java.util.function.BiFunction;

public class Fractions {
    public static void main(String[] args) {
        /** Сумма двух дробей */
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        System.out.println(add.apply(1.2, 3.4)); // Output: 4.6

        /** Разница двух дробей */
        BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
        System.out.println(subtract.apply(1.2, 3.4)); // Output: -2.2

        /** Произведение  двух дробей */
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        System.out.println(multiply.apply(1.2, 3.4)); // Output: 4.08

        /** Деление двух дробей */
        BiFunction<Double, Double, Double> divide = (a, b) -> a / b;
        System.out.println(divide.apply(1.2, 3.4)); // Output: 0.35294117647058826
    }
}
