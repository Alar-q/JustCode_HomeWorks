import java.util.Arrays;
import java.util.Random;

public class Fourth_Array {
    public static void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();

        // Заполнение массива случайными числами от -10 до 10
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(21) - 10;
        }

        // Вывод исходного массива
        System.out.println("Исходный массив: " + Arrays.toString(array));

        // Вычисление среднего значения массива
        double average = calculateAverage(array);
        System.out.println("Среднее арифметическое: " + average);

        // Создание нового массива с элементами, меньше среднего
        int[] newArray = lessThanAverage(array, average);
        System.out.println("Значения меньше среднего: " + Arrays.toString(newArray));

        // Сортировка исходного массива
        bubbleSort(array);
        System.out.println("Отсортированный исходный массив: " + Arrays.toString(array));
    }

    public static double calculateAverage(int[] array) {
        int sum = 0;
        for (int number : array) {
            sum += number;
        }
        return (double) sum / array.length;
    }

    public static int[] lessThanAverage(int[] array, double average) {
        int[] tempArray = new int[array.length];
        int index = 0;

        for (int number : array) {
            if (number < average) {
                tempArray[index++] = number;
            }
        }

        // Усечение массива до фактической длины
        int[] newArray = new int[index];
        System.arraycopy(tempArray, 0, newArray, 0, index);
        return newArray;
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }
}
