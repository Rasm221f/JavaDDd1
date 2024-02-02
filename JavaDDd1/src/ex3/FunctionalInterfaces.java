package ex3;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Random;


public class FunctionalInterfaces{
    public static void main(String[] args) {

    List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
    Random random = new Random();

    Supplier<Stream<Employee>> employeeSupplier = () -> names.stream().map(Employee::new);
    List<Employee> employees = employeeSupplier.get().collect(Collectors.toList());

    Consumer<List<Employee>> printEmployees = list -> list.forEach(employee -> System.out.println(employee.getName()));
    printEmployees.accept(employees);

    Function<List<Employee>, List<String>> toNames = list -> list.stream().map(Employee::getName).collect(Collectors.toList());
    List<String> namesList = toNames.apply(employees);

    LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

    Predicate<Employee> isOlderThan18 = employee -> !employee.getBirthday().isAfter(eighteenYearsAgo);


        List<Employee> randomEmployees = IntStream.range(0, 10) // Generer 10 medarbejdere
                .mapToObj(i -> new Employee(
                        names.get(random.nextInt(names.size())), // Tilfældig navn
                        LocalDate.of(
                                random.nextInt(21) + 1995, // Vilkårligt år mellem 1995 og 2015
                                random.nextInt(12) + 1,    // Vilkårlig måned
                                random.nextInt(28) + 1     // Vilkårlig dag
                        )
                ))
                .collect(Collectors.toList());


        Period period = Period.between(randomEmployees.getLast().birthday, LocalDate.now());

        System.out.println(period);

        randomEmployees.forEach(employee -> {
            System.out.println(employee + " is older than 18: " + isOlderThan18.test(employee));
        });




    }
}