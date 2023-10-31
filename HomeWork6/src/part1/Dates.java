package part1;

import java.time.LocalDate;
import java.util.function.Predicate;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class Dates {
    /** Так же есть встроенные функциональные интерфейсы Java */
    private interface BiFunction<T, E, R> {
        R apply(T t, E e);
    }
    private interface Function<T, R> {
        R apply(T t);
    }
    public static void main(String[] args) {
        /** Проверка является ли год високосным */
        Predicate<Integer> isLeapYear = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(isLeapYear.test(2020)); // Output: true

        /** Подсчет количества дней между двумя датами */
        BiFunction<LocalDate, LocalDate, Long> daysBetween = (start, end) -> DAYS.between(start, end);
        System.out.println(
            daysBetween.apply(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)
            )
        ); // Output: 364

        /** Подсчет количества недель между двумя датами */
        BiFunction<LocalDate, LocalDate, Long> weeksBetween = (start, end) -> WEEKS.between(start, end);
        System.out.println(
            weeksBetween.apply(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)
            )
        ); // Output: 52

        /** Подсчёт дня недели по полученной дате */
        Function<LocalDate, String> dayOfWeek = date -> date.getDayOfWeek().toString();
        System.out.println(
            dayOfWeek.apply(
                    LocalDate.of(1969, 7, 20)
            )
        ); // Output: SUNDAY
    }
}
