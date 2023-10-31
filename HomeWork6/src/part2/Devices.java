package part2;

import java.util.Arrays;
import java.util.List;

public class Devices {
    public static void main(String[] args) {
        List<Device> devices = Arrays.asList(
                new Device("Samsung", 2020, 200, "Black", "Type1"),
                new Device("IPhone", 2021, 300, "White", "Type2"),
                new Device("Nokia", 2009, 100, "Green", "Type1")
                // more devices here
        );

        // Показать все устройства
        System.out.println("Показать все устройства");
        devices.forEach(device -> System.out.println(device.name));

        // Показать все устройства заданного цвета
        System.out.println("\nПоказать все устройства заданного цвета (Black)");
        devices.stream()
                .filter(device -> "Black".equals(device.color))
                .forEach(System.out::println);

        // Показать все устройства заданного года выпуска
        System.out.println("\nПоказать все устройства заданного года выпуска (2009)");
        devices.stream()
                .filter(device -> device.year == 2009)
                .forEach(System.out::println);

        // Показать все устройства дороже заданной цены
        System.out.println("\nПоказать все устройства дороже заданной цены (100)");
        devices.stream()
                .filter(device -> device.price > 100)
                .forEach(System.out::println);

        // Показать все устройства заданного типа
        System.out.println("\nПоказать все устройства заданного типа (Type1)");
        devices.stream()
                .filter(d -> d.type.equalsIgnoreCase("Type1"))
                .forEach(System.out::println);

        // Показать все устройства, чей год выпуска находится в указанном диапазоне
        System.out.println("\nПоказать все устройства, чей год выпуска находится в указанном диапазоне (2009-2021)");
        devices.stream()
                .filter(d -> d.year > 2009  && d.year < 2021)
                .forEach(System.out::println);
    }
}
