import java.util.Scanner;

public class Third_DrawTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество ярусов:");
        int tiers = scanner.nextInt();

        System.out.println("Введите высоту первого яруса:");
        int height = scanner.nextInt();

        drawTree(tiers, height);
    }

    public static void drawTree(int tiers, int height) {
        int width = height * 2 - 1;

        for (int i = 0; i < tiers; i++) {

            for (int j = 0; j < height; j++) {
                for(int l=0; l<tiers-i; l++){
                    System.out.print(" ");
                }

                for (int k = 0; k <= width / 2 + j; k++) {
                    if (k < width / 2 - j) {
                        System.out.print(" ");
                    } else { // Находится в диапозоне (j)***(j)
                        System.out.print("*");
                    }
                }
                System.out.println();
            }

            height++;
            width += 2;
        }
    }
}
