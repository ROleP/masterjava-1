package net.rolep.jpractice.threads.syncing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by rolep on 3/16/17.
 */
public class SimpleSync {

    int count = 0;
    void increment() {
        count++;
    }

    synchronized void incrementSync() {
        count++;
    }

    ReentrantLock lock = new ReentrantLock();

    void incrementLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    void calculate() throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, 100000)
                .forEach(i -> executor.submit(this::incrementSync));

        executor.shutdown();
        while (!executor.isTerminated()) Thread.sleep(50);
        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleSync().calculate();
    }
}
