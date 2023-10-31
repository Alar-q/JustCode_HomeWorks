package part2;

import java.util.Comparator;
import java.util.List;

public class Projector {
    String name;
    int year;
    int price;
    String manufacturer;

    public Projector(String name, int year, int price, String manufacturer) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return String.format("%s Details: \n" +
                        "Year: %d \n" +
                        "Price: %d \n" +
                        "Manufacturer: %s\n",
                name, year, price, manufacturer);
    }


    public static void displayProjectors(List<Projector> projectors) {
        projectors.forEach(System.out::println);
    }

    public static void displayByManufacturer(List<Projector> projectors, String manufacturer) {
        projectors.stream()
                .filter(p -> p.manufacturer.equalsIgnoreCase(manufacturer))
                .forEach(System.out::println);
    }

    public static void displayCurrentYear(List<Projector> projectors, int currentYear) {
        projectors.stream()
                .filter(p -> p.year == currentYear)
                .forEach(System.out::println);
    }

    public static void displayAbovePrice(List<Projector> projectors, int price) {
        projectors.stream()
                .filter(p -> p.price > price)
                .forEach(System.out::println);
    }

    public static void displaySortedByPrice(List<Projector> projectors) {
        projectors.stream()
                .sorted(Comparator.comparing(Projector::getPrice))
                .forEach(System.out::println);
    }

    public static void displaySortedByYear(List<Projector> projectors) {
        Comparator<Projector> comparator = Comparator.comparingInt(Projector::getYear);
        comparator = comparator.reversed();
        projectors.stream().sorted(comparator).forEach(System.out::println);
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}
