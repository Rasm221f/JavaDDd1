package ex6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookOperations {
    public static void main(String[] args) {
        Random random = new Random();
        List<String> bookNames = Arrays.asList("Harry Potter", "Pride and Prejudice", "Frankenstein", "The Hobbit", "No time for goodbyes");
        List<String> authorNames = Arrays.asList("Jane Austin", "J.K. Rowling", "Charles Dickens", "Hans Christian Andersen", "Elizabeth Smart");

        List<Book> bookList = IntStream.range(0, bookNames.size())
                .mapToObj(i -> new Book(
                        bookNames.get(i),
                        authorNames.get(random.nextInt(authorNames.size())),
                        LocalDate.of(random.nextInt(74) + 1950,
                                random.nextInt(12) + 1,
                                random.nextInt(28) + 1),
                        random.nextInt(300, 500),
                        random.nextDouble(6.0, 9.9)
                ))
                        .
                collect(Collectors.toList());

        double totalRating = bookList.stream()
                .mapToDouble(book -> book.getRating())
                .sum();

        double averageRating = totalRating / (double) bookList.size();

        //System.out.println(averageRating);

        LocalDate year2000 = LocalDate.now().minusYears(24);

        Predicate<Book> isItPublishedAfter2000 = book ->
                book.getPublicationYear().isAfter(year2000);

        List<Book> BooksPublishedAfter2000 = bookList.stream()
                .filter(isItPublishedAfter2000)
                .collect(Collectors.toList());

        //System.out.println("Number of books published after 2000: " + BooksPublishedAfter2000.size());
        //BooksPublishedAfter2000.forEach(book -> System.out.println(book.getTitle()));

        List<Book> SortedByRating = bookList.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());

       /* SortedByRating.forEach(book -> System.out.println(book.getTitle() + ": " +
                BigDecimal.valueOf(book.getRating())
                        .setScale(2, RoundingMode.HALF_UP)));  */

        Map<String, Double> authorRatings = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.averagingDouble(Book::getRating)
                ));

        // Sort the authors by name and print the books and average ratings
        authorRatings.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": Average Ratings = " + entry.getValue());
                    // Print the books for this author
                    bookList.stream()
                            .filter(book -> book.getAuthor().equals(entry.getKey()))
                            .sorted(Comparator.comparing(Book::getTitle)) // Assuming you want to sort the books by title
                            .forEach(book -> System.out.println("\t" + book.getTitle() + " - Rating: " + book.getRating()));
                });

    }
}
