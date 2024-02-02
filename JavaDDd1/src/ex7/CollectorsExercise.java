package ex7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsExercise {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 200.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        // Calculate the total sum of all transaction amounts
        double totalSum = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total sum of all transactions: " + totalSum);

        // Group transactions by currency and calculate sum for each currency
        Map<String, Double> sumPerCurrency = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency,
                                Collectors.summingDouble(Transaction::getAmount)));
        sumPerCurrency.forEach((currency, sum) -> System.out.println(currency + ": " + sum));



        // Find the highest transaction amount

        // Find the average transaction amount
    }
}
