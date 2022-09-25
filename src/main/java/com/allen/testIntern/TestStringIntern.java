package com.allen.testIntern;

/**
 * @Author: allen
 * @Date: 2022/7/5 10:49
 * @Description:
 * 1、使用 new 创建时，检测常量池中是否存在对应String对象的char数组,如果不存在则在字符串常量池中创建一个string，最后在堆中创建String对象。
 * 2、如果直接使用字面量，则先看常量池中是否有相等的字符串，如果有，则返回此字符串，如果没有，则在常量池中创建
 *
 * 调用intern()方法时，首先在常量池中查看是否已有相同的字符串（字符串是否相同使用String的equal方法判断），
 * 如果常量池中已有，则直接返回该字符串的地址。如果字符串常量池中不存在，就在常量池中创建一个指向该对象堆中实例的引用，并返回这个引用。
 **/
public class TestStringIntern {

    public static void main(String[] args) {
        String s1 = "performance";
        String s2 = new String("performance");
        String s3 = s2.intern();
        // false
        System.out.println(s1 == s2);
        // true
        System.out.println(s1 == s3);
        // false
        System.out.println(s2 == s3);
        System.out.println("--------------------------");

        String s4 = "hello";
        String s5 = "word";
        // 在堆中创建helloword String对象
        String s6 = s4 + s5;
        String s8 = "helloword";
        // 将上面String对象的地址存入常量池
        String s7 = s6.intern();
        // 常量池中已经存在 helloword字符串的引用，直接返回该引用
//        String s8 = "helloword";
        // false
        System.out.println(s6 == s7);
        // true
        System.out.println(s8 == s7);
        // false
        System.out.println(s8 == s6);
        System.out.println("--------------------------");

//        String a = new String("perftuning").intern();
//        String b = new String("perftuning").intern();
//        System.out.println(a == b);
    }
}
