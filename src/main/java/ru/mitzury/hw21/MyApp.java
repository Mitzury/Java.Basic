package ru.mitzury.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyApp {

    private static final Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();

    private static char currentLetter = 'A';

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(() -> printLetter('A', 'B', conditionA, conditionB));
        executor.execute(() -> printLetter('B', 'C', conditionB, conditionC));
        executor.execute(() -> printLetter('C', 'A', conditionC, conditionA));

        executor.shutdown();
    }

    private static void printLetter(
            char myLetter,
            char nextLetter,
            Condition myCondition,
            Condition nextCondition
    ) {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                while (currentLetter != myLetter) {
                    myCondition.await();
                }
                System.out.print(myLetter);
                currentLetter = nextLetter;
                nextCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
