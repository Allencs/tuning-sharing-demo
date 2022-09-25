package com.allen.lockElimination;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: allen
 * @Date: 2022/9/8 13:51
 * @Description: 锁消除案例
 */
@State(Scope.Benchmark)
public class LockElimination {

    StringBuffer buffer = new StringBuffer();
    // 锁粗化
    public void append(){
        buffer.append("aaa").append(" bbb").append(" ccc");
    }

    /**
     * 锁消除
     * -XX:+EliminateLocks 开启锁消除(jdk8默认开启）
     * -XX:-EliminateLocks 关闭锁消除
     * @param str1
     * @param str2
     */
    public StringBuffer append(String str1, String str2) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
        return sb;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    public void testLockElimination() {
        LockElimination demo = new LockElimination();
        for (int i = 0; i < 100000000; i++) {
            demo.append("aaa", "bbb");
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LockElimination.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
