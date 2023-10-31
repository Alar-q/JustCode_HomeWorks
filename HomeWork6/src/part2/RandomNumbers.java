package part2;

import java.util.Arrays;

public class RandomNumbers {
    public static void main(String[] args) {
        // опустим момент со случайной генерацией
        /*int n = (int) Math.random() * 1000;
        int[] numbers = new int[n];
        for(int i=0; i<n; i++){
            numbers[i] = (int) Math.random() * 10000;
        }*/

        int[] numbers = {-1, 2, 13, -15, 121, 1221, 55};

        //        Количество положительных;
        long positiveCount = Arrays.stream(numbers).filter(num -> num > 0).count();
        //        Количество отрицательных;
        long negativeCount = Arrays.stream(numbers).filter(num -> num < 0).count();
        //        Количество двухзначных;
        long twoDigitCount = Arrays.stream(numbers).filter(num -> num >= 10 && num <= 99 || num <= -10 && num >= -99).count();
        //        Количество зеркальных чисел. Например, 121 или 4224.
        long mirrorNumbersCount = Arrays.stream(numbers).filter(RandomNumbers::isMirrorNumber).count();

        System.out.println("Positive numbers: " + positiveCount);
        System.out.println("Negative numbers: " + negativeCount);
        System.out.println("Two-digit numbers: " + twoDigitCount);
        System.out.println("Mirror numbers: " + mirrorNumbersCount);
    }

    public static boolean isMirrorNumber(int number) {
        String strNum = String.valueOf(Math.abs(number));
        return new StringBuilder(strNum).reverse().toString().equals(strNum);
    }
}