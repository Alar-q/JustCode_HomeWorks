public class ArmstrongNumbers {
    public static void main(String[] args) {
        for (int i = 0; i <= 10000000; i++) {
            if (isArmstrongNumber(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isArmstrongNumber(int number) {
        int n = number;
        int digits = String.valueOf(number).length();
        int sum = 0;
        int temp;

        while (n != 0) {
            temp = n % 10;
            sum += Math.pow(temp, digits);
            n /= 10;
        }

        return sum == number;
    }
}
