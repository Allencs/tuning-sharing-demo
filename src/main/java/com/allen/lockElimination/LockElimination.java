package com.allen.lockElimination;

/**
 * @Author: allen
 * @Date: 2022/9/8 13:51
 * @Description: 锁消除案例
 */
public class LockElimination {

    StringBuilder sb = new StringBuilder();

    public String eliminateLockMethod(String a, String b) {
        StringBuffer sb = new StringBuffer();
        sb.append(a);
        sb.append(b);
        return sb.toString();
    }

    public String normalLockMethod(String a, String b) {
        synchronized (this) {
            sb.append(a);
            sb.append(b);
            return sb.toString();
        }
    }

}
