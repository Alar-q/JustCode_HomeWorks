import java.util.Random;
import java.util.Scanner;

public class Fifth_DoubleArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Количество строк:");
        int rows = scanner.nextInt();

        System.out.println("Количество столбцов:");
        int cols = scanner.nextInt();

        int[][] array = initializeArray(rows, cols);
        printArray(array);

        System.out.println("Номер строки, которую нужно удалить:");
        int rowToDelete = scanner.nextInt();

        array = deleteRow(array, rowToDelete);
        printArray(array);
    }

    public static int[][] initializeArray(int rows, int cols) {
        Random random = new Random();
        int[][] array = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(11);
            }
        }

        return array;
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            System.out.print("[ ");
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("]");
        }
    }

    public static int[][] deleteRow(int[][] array, int rowToDelete) {
        int rows = array.length;
        int cols = array[0].length;

        int[][] newArray = new int[rows - 1][cols];

        // Подмассивы остаются теми же
        for (int i = 0, k = 0; i < rows; i++) {
            if (i == rowToDelete) {
                continue;
            }
            newArray[k++] = array[i];
        }

        return newArray;
    }
}
