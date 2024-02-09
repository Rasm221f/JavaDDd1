package Threadsex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoreUtilizationProgram {

    public static void main(String[] args) {
        // Get the number of available cores
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores available: " + cores);

        ExecutorService executor = Executors.newFixedThreadPool(cores);

        // Submit tasks to the executor service
        for (int i = 0; i < cores; i++) {
            int lowerBound = i * 10000 + 1; // Starting number for prime calculation for each task
            int upperBound = (i + 1) * 10000; // Ending number for prime calculation for each task
            executor.submit(new PrimeCalculator(lowerBound, upperBound));
        }

        // Initiates an orderly shutdown
        executor.shutdown();
    }

    private static class PrimeCalculator implements Runnable {
        private final int lowerBound;
        private final int upperBound;

        PrimeCalculator(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started calculating primes between " + lowerBound + " and " + upperBound);
            for (int number = lowerBound; number <= upperBound; number++) {
                if (isPrime(number)) {
                 System.out.println(number);
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}

