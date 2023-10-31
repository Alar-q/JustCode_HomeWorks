import exceptions.AggregationException;
import exceptions.EmptyListException;
import exceptions.NoMatchesException;
import exceptions.SortingException;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> people = new ArrayList<>(Arrays.asList(
                    new Person("Charlie", 40),
                    new Person("Alice", 30),
                    new Person("Bob", 20),
                    new Person("Max", 10),
                    new Person(null, 30)
            ));

            if (people.isEmpty()){
                throw new EmptyListException("Список пуст.");
            }

            // Фильтрация
            int ageLimit = 25;
            people = people.stream()
                    .filter(p -> p.age > ageLimit)
                    .collect(Collectors.toList());

            if (people.isEmpty()) {
                throw new NoMatchesException("Нет данных, соответствующих условию.");
            }

            System.out.println("Сортировка: " + people);
            // Сортировка
            try {
                people.sort(Comparator.comparing(p -> p.name));
                // Collections.sort(people, (Person p1, Person p2)->{
                //    return p1.name.compareTo(p2.name);
                // });
            }
            catch(NullPointerException e){
                throw new SortingException("Сортировка не удалась.");
            }

            System.out.println(people);

            // Агрегация
            String names = people.stream()
                    .map(p -> {
                        if (p.name == null) {
                            throw new RuntimeException(new AggregationException("Агрегация данных не удалась."));
                        }
                        return p.name;
                    })
                    .collect(Collectors.joining(", "));

            System.out.println(names);

        }
        catch (EmptyListException | NoMatchesException | SortingException e){
            System.err.println(e.getMessage());
        }
        catch (RuntimeException e) {
            System.err.println(e.getCause().getMessage());
        }
    }
}