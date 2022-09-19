package com.allen.objectMemLayout;

import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: allen
 * @Date: 2022/9/19 17:52
 * @Description:
 */
public class LockStatus {

    private final static Logger log = LoggerFactory.getLogger(LockStatus.class);

    private final Object lock = new Object();

    public void test() {
        log.debug(Thread.currentThread().getName() + " start");
        synchronized (lock) {
            log.debug(Thread.currentThread().getName() + " execute");
            try {
                //Thread.sleep(5000);
                lock.wait(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug(Thread.currentThread().getName() + " end");
        }

    }

    public static void main(String[] args) {
        final LockStatus lockStatus = new LockStatus();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockStatus.test();
                    System.out.println(ClassLayout.parseInstance(lockStatus.lock).toPrintable());
                }
            }, "thread" + i).start();
        }

    }
}
