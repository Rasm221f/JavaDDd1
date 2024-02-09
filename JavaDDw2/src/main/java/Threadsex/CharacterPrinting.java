package Threadsex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CharacterPrinting {

    public static void main(String[] args) {
        // Create an ExecutorService with 4 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit 25 tasks to the ExecutorService
        for (char ch = 'A'; ch <= 'Y'; ch++) { // Iterating from 'A' to 'Y'
            final String text = String.valueOf(ch).repeat(3); // Java 11 repeat method
            executor.submit(() -> System.out.println(text));
        }

        // Shutdown the ExecutorService gracefully once all tasks are completed
        executor.shutdown();
    }
}
