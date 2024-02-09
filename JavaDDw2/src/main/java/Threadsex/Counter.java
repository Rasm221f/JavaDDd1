package Threadsex;

public class Counter {
    private int count = 0;

    // Method to increment the count, synchronized to ensure thread safety
    public synchronized void increment() {
        count++;
    }

    // Method to retrieve the current count value, synchronized to ensure thread safety
    public synchronized int getCount() {
        return count;
    }
}
