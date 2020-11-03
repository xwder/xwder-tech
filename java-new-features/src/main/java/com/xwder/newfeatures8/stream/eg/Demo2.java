package com.xwder.newfeatures8.stream.eg;

import com.xwder.newfeatures8.stream.eg.model.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 筛选与切片
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Demo2 {

    //示例集合
    private List<Dish> menuList = Arrays.asList(
            new Dish("猪肉", false, 800, Dish.Type.MEAT),
            new Dish("牛肉", false, 700, Dish.Type.MEAT),
            new Dish("鸡肉", false, 400, Dish.Type.MEAT),
            new Dish("薯条", true, 530, Dish.Type.OTHER),
            new Dish("米饭", true, 350, Dish.Type.OTHER),
            new Dish("水果", true, 120, Dish.Type.OTHER),
            new Dish("比萨饼", true, 550, Dish.Type.OTHER),
            new Dish("大虾", false, 300, Dish.Type.FISH),
            new Dish("龙利鱼", false, 450, Dish.Type.FISH));

    /**
     * 使用filter()方法筛选执行元素
     * 输出如下：
     * Dish{name='薯条', vegetarian=true, calories=530, type=OTHER}
     * Dish{name='米饭', vegetarian=true, calories=350, type=OTHER}
     * Dish{name='水果', vegetarian=true, calories=120, type=OTHER}
     * Dish{name='比萨饼', vegetarian=true, calories=550, type=OTHER}
     */
    @Test
    public void testFilter() {
        List<Dish> vegetarianMenu = menuList.stream()
                .filter(Dish::isVegetarian) //筛选出素食菜肴
                .collect(Collectors.toList());
        //遍历
        vegetarianMenu.forEach(System.out::println);
    }

    /**
     * 使用distinct()方法筛选各异的元素
     * 输出如下：
     * 2
     * 4
     */
    @Test
    public void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0) //筛选出偶数
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 使用limit(n)方法，返回一个不超过给定长度的流
     * 输出如下：
     * 猪肉:800
     * 牛肉:700
     * 鸡肉:400
     */
    @Test
    public void testLimit() {
        //提取卡路里超过300的前3个的菜肴
        List<Dish> threeDishs = menuList.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        threeDishs.forEach(dish -> System.out.println(dish.getName() + ":" + dish.getCalories()));
    }

    /**
     * 使用skip(n)方法，返回一个扔掉了前n个元素的流
     * 输出如下：
     * 薯条:530
     * 米饭:350
     * 比萨饼:550
     * 龙利鱼:450
     */
    @Test
    public void testSkip() {
        //跳过超过300卡路里的头三道菜，并返回剩下的
        List<Dish> threeDishs = menuList.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(3)
                .collect(Collectors.toList());
        threeDishs.forEach(dish -> System.out.println(dish.getName() + ":" + dish.getCalories()));
    }

}