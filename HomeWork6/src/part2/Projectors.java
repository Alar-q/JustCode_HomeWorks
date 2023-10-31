package part2;

import java.util.Arrays;
import java.util.List;

public class Projectors {
    public static void main(String[] args) {
        List<Projector> projectors = Arrays.asList(
                new Projector("Proj1", 2022, 1000, "Sony"),
                new Projector("Proj2", 2023, 1500, "Samsung")
        );

        System.out.println("Показать все проекторы");
        Projector.displayProjectors(projectors);

        System.out.println("Показать все проекторы одного производителя");
        Projector.displayByManufacturer(projectors, "Sony");

        System.out.println("Показать все проекторы текущего года");
        Projector.displayCurrentYear(projectors, 2023);

        System.out.println("Показать все проекторы дороже заданной цены");
        Projector.displayAbovePrice(projectors, 1200);

        System.out.println("Показать все проекторы, отсортированные по цене по возрастанию");
        Projector.displaySortedByPrice(projectors);

        System.out.println("Показать все проекторы, отсортированные по году выпуска по убыванию");
        Projector.displaySortedByYear(projectors);
    }
}
