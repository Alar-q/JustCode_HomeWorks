package part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Products {
    public static void main(String[] args) {
        new Products().main();
    }

    enum Title {
        Milk,
        Cheese,
        Butter,
        Pasta,
        Cake,
        Bread;
    }
    enum Category {
        MilkProducts,
        FlourProducts,
    }

    class Product {
        private Title title;
        private Category category;
        private int id;

        public Product(Title title, Category category){
            this.title = title;
            this.category = category;
            this.id = (int) (Math.random() * 100000);
        }
        public Title getTitle(){
            return this.title;
        }
        public Category getCategory(){
            return this.category;
        }
        @Override
        public String toString(){
            return this.category.toString() + ": " + this.title.toString() + "  (id:"+this.id+")";
        }
    }

    void main(){
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product(Title.Milk, Category.MilkProducts),
                new Product(Title.Milk, Category.MilkProducts),
                new Product(Title.Cheese, Category.MilkProducts),
                new Product(Title.Butter, Category.MilkProducts),
                new Product(Title.Pasta, Category.FlourProducts),
                new Product(Title.Bread, Category.FlourProducts),
                new Product(Title.Cake, Category.FlourProducts)
        ));


        System.out.println("Показать все продукты");
        productList.forEach(System.out::println);


        System.out.println("\nПоказать все продукты с названием меньше пяти символов");
        productList.stream()
                .filter(product -> product.getTitle().toString().length() < 5)
                .forEach(System.out::println);


        System.out.println("\nПосчитать сколько раз встречается продукт, чье название ввёл пользователь");
        String userInput = "Milk";
        long count = productList.stream()
                .filter(product -> product.getTitle().toString().equalsIgnoreCase(userInput))
                .count();
        System.out.println(userInput + " appears " + count + " times.");


        System.out.println("\nПоказать все продукты, которые начинаются на заданную букву");
        char startingLetter = 'M';
        productList.stream()
                .filter(product -> product.getTitle().toString().charAt(0) == startingLetter)
                .forEach(System.out::println);


        System.out.println("\nПоказать все продукты из категории «Молоко»");
        productList.stream()
                .filter(product -> product.getCategory() == Category.MilkProducts)
                .forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nПосчитать сколько раз встречается продукт, чье название ввёл пользователь");
            String name = scanner.next();
            count = productList.stream()
                    .filter(product -> product.getTitle().toString().equalsIgnoreCase(name))
                    .count();
            System.out.println(name + " appears " + count + " times.");
        }
    }

}
