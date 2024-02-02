package ex4;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TIMEAPI {
    public static void main(String[] args) {
        Random random = new Random();

        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");

        List<Employee> randomEmployees = IntStream.range(0, 10) // Generer 10 medarbejdere
                .mapToObj(i -> {

                        int year =         random.nextInt(21) + 1995; // Vilkårligt år mellem 1995 og 2015
                        int month =        random.nextInt(12) + 1;    // Vilkårlig måned
                        int day = random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth()) + 1; // Random day within the valid range for the month
                     return new Employee(
                             names.get(random.nextInt(names.size())),
                                LocalDate.of(year,month,day)
                );
    })
                .collect(Collectors.toList());

        Consumer<Employee> printNameAndAge = employee -> {
            LocalDate today = LocalDate.now();
            Period age = Period.between(employee.getBirthday(), today);
            System.out.println(employee.getName() + " is " + age.getYears() + " years old.");
        };

        int totalAge = randomEmployees.stream()
                .mapToInt(employee -> Period.between(employee.getBirthday(), LocalDate.now()).getYears())
                .sum();

        double averageAge = totalAge / (double) randomEmployees.size();

        //Calculate the average age of all employees
        //System.out.println("Employees are on average " + averageAge + " years old.");

        //Calculate the age of each employee based on their birthdate
        //randomEmployees.forEach(printNameAndAge);

        Predicate<Employee> BirthDayThisMonth = employee -> {
            return employee.getBirthday().getMonth() == LocalDate.now().getMonth();
        };
        //Filter and display employees who have birthdays in a specific month.
        // List all employees who has a birthday in the current month.
        List<Employee> employeesWithBirthDayThisMonth = randomEmployees.stream().filter(BirthDayThisMonth).collect(Collectors.toList());
        if (employeesWithBirthDayThisMonth.isEmpty()) {
            System.out.println("No employees have birthdays this month.");
        } else {
            System.out.println("Employees with birthdays this month:");
            employeesWithBirthDayThisMonth.forEach(employee ->
                    System.out.println(employee.getName() + ", Birthday: " + employee.getBirthday()));
        }
        //Group employees by birth month and display the count of employees in each group.
        Map<Month, Long> employeesByBirthMonth = randomEmployees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getBirthday().getMonth(),
                        Collectors.counting()
                ));
        employeesByBirthMonth.forEach((month, count) -> {
            System.out.println(month + ": " + count + " employee(s)");
        });

    }

}
