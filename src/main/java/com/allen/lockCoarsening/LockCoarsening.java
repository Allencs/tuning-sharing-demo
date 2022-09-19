package com.allen.lockCoarsening;

/**
 * @Author: allen
 * @Date: 2022/9/19 23:12
 * @Description: 对同一个对象连续反复加锁和解锁，
 * 即使没有出现线程竞争，加锁和解锁的开销也会导致不必要的性能损耗；
 **/
public class LockCoarsening {

    StringBuilder stringBuilder = new StringBuilder();
    final Object lock = new Object();

    public void test(){
        for(int i=0; i<100000; i++){
            synchronized(lock){
                stringBuilder.append("1");
            }
        }
    }

    public static void main(String[] args) {
        LockCoarsening lockCoarsening = new LockCoarsening();
        lockCoarsening.test();
    }
}
