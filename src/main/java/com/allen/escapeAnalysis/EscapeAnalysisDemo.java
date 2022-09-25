package com.allen.escapeAnalysis;

/**
 * @Author: allen
 * @Date: 2022/9/25 14:28
 * @Description: 逃逸分析案例
 *
 *  关闭逃逸分析，同时调大堆空间，避免GC影响，如果有GC信息将会被打印出来
 *  VM运行参数：-Xmx2G -Xms2G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *
 *  开启逃逸分析  jdk8默认开启
 *  VM运行参数：-Xmx2G -Xms2G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 **/
public class EscapeAnalysisDemo {

    public static void demo_1() {
        for (int i = 0; i < 500000; i++) {
            alloc();
        }
        System.out.println("对象创建完毕");
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Point alloc() {
        Point point = new Point(1,2);
        return point;
    }

    public static void main(String[] args) {
        EscapeAnalysisDemo.demo_1();
    }
}
