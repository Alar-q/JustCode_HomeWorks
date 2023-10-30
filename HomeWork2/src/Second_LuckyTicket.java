import java.util.Scanner;

public class Second_LuckyTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Введите шестизначное число:");
            int number = scanner.nextInt();

            System.out.println(isLuckyTicket(number) ? "Да" : "Нет");
        }
    }

    public static boolean isLuckyTicket(int number) {
        int firstPart = number / 1000;
        int secondPart = number % 1000;

        int sumFirstPart = sumOfDigits(firstPart);
        int sumSecondPart = sumOfDigits(secondPart);

        return sumFirstPart == sumSecondPart;
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
