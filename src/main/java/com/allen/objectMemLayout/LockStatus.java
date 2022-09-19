package com.allen.objectMemLayout;

import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: allen
 * @Date: 2022/9/19 17:52
 * @Description: 锁对象是class对象，直接是轻量级锁
 */
public class LockStatus {

    private final static Logger log = LoggerFactory.getLogger(LockStatus.class);

    public static void main(String[] args) {
        try {
            // HotSpot 虚拟机在启动后有 4s 的延迟才会对每个新建的对象开启偏向锁模式
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final Object lock = new Object();

        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    log.debug(Thread.currentThread().getName() + " start");
//                    synchronized (LockStatus.class) {
                    synchronized (lock) {
                        log.debug(Thread.currentThread().getName() + " execute");
                        try {
                            Thread.sleep(3000);
//                lock.wait(5000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.debug(Thread.currentThread().getName() + " end");
                        System.out.println(Thread.currentThread().getName() + "\n" + ClassLayout.parseInstance(lock).toPrintable());
//                        System.out.println(Thread.currentThread().getName() + "\n" + ClassLayout.parseInstance(LockStatus.class).toPrintable());
                    }

                }
            }, "thread-" + i).start();
        }


    }
}
