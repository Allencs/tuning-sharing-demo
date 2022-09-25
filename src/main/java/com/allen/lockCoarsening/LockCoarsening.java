package com.allen.lockCoarsening;


/**
 * @Author: allen
 * @Date: 2022/9/19 23:12
 * @Description: 对同一个对象连续反复加锁和解锁，
 * 即使没有出现线程竞争，加锁和解锁的开销也会导致不必要的性能损耗；
 * 以下为方便解释原理的伪代码
 **/
public class LockCoarsening {
    final Object lock = new Object();

//    public void coarsening_1() {
//        synchronized (lock) {
//            System.out.println("hello");
//        }
//        synchronized (lock) {
//            System.out.println("world");
//        }
//        synchronized (lock) {
//            System.out.println("!");
//        }
//    }

    public void coarsening_1() {
        synchronized (lock) {
            System.out.println("hello");
            System.out.println("world");
            System.out.println("!");
        }
    }

//    public void coarsening_2() {
//        for (inti = 0; i < CIRCLE; i++) {
//            synchronized (lock) {
//                doSomethingFast();
//            }
//        }
//    }

//    public void coarsening_2() {
//        synchronized (lock) {
//            for (inti = 0; i < CIRCLE; i++) {
//                doSomethingFast();
//            }
//        }
//    }
}
