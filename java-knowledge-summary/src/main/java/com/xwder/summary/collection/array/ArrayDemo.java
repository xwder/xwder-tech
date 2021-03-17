package com.xwder.summary.collection.array;

/**
 * 数组初始化方式 三种 <p>
 *
 * 注意：数组完成初始化后，内存空间中针对该数组的各个元素就有个一个默认值： <p>
 *      基本数据类型的整数类型（byte、short、int、long）默认值是0； <p>
 *      基本数据类型的浮点类型（float、double）默认值是0.0； <p>
 *      基本数据类型的字符类型（char）默认值是'\u0000'； <p>
 *      基本数据类型的布尔类型（boolean）默认值是false； <p>
 *      类型的引用类型（类、数组、接口、String）默认值是null. <p>
 *
 * @author: xwder
 * @date: 2021/3/15
 **/
public class ArrayDemo {

    public static void main(String[] args) {
        // 一.静态初始化：
        // 1.初始化时由程序员显式指定每个数组元素的初始值，有系统决定数组的长度；
        int[] intArr = new int[]{1, 2, 3};

        // 2.简化的静态初始化方式    type[] arrayName = {element1,element2,element3...};
        String[] strArr = {"张三", "李四", "王二麻"};

        // 二.动态初始化：初始化时由程序员指定数组的长度，由系统初始化每个数组元素的默认值。
        // arrayName = new type[length];
        int[] price = new int[4];

        // 不要同时使用静态初始化和动态初始化，也就是说，不要在进行数组初始化时，既指定数组的长度，也为每个数组元素分配初始值。
        // 一旦数组完成初始化，数组在内存中所占的空间将被固定下来，所以数组的长度将不可改变。
    }
}
