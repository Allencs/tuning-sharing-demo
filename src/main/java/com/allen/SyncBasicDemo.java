package com.allen;

/**
 * @Author: allen
 * @Date: 2022/9/20 16:13
 * @Description:
 */
public class SyncBasicDemo {

    private static volatile int counter = 0;

    private final Object lock = new Object();

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public void decrement_1() {
        synchronized (lock) {
            counter--;
        }
    }
}
