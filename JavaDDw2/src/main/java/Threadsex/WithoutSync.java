package Threadsex;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WithoutSync {
    private static List<Integer> list = new CopyOnWriteArrayList<>();

    public void addCount(int count) {
        list.add(count);
        System.out.println("Task: " + count + ": List size = " + list.size());
    }
}
