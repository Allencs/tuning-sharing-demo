package com.allen.objectMemLayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: allen
 * @Date: 2022/9/19 17:46
 * @Description: 对象内存布局
 */
public class ObjectMemLayout {

    public static void main(String[] args) {
        Object obj = new Object();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable()); }

}
