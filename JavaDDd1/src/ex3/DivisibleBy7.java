package ex3;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DivisibleBy7 {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 7, 14, 20, 28, 35, 42, 50);
        Predicate<Integer> isDivisibleBy7 = num -> num % 7 == 0;
        List<Integer> filteredNumbers = numbers.stream().filter(isDivisibleBy7).collect(Collectors.toList());
        System.out.println(filteredNumbers);
    }
}
