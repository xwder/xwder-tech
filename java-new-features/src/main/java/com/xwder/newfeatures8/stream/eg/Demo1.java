package com.xwder.newfeatures8.stream.eg;

import com.xwder.newfeatures8.stream.eg.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 关于Stream API的第一个示例
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Demo1 {

    public static void main(String[] args) {
        //示例集合
        List<Dish> menuList = Arrays.asList(
                new Dish("猪肉", false, 800, Dish.Type.MEAT),
                new Dish("牛肉", false, 700, Dish.Type.MEAT),
                new Dish("鸡肉", false, 400, Dish.Type.MEAT),
                new Dish("薯条", true, 530, Dish.Type.OTHER),
                new Dish("米饭", true, 350, Dish.Type.OTHER),
                new Dish("水果", true, 120, Dish.Type.OTHER),
                new Dish("比萨饼", true, 550, Dish.Type.OTHER),
                new Dish("大虾", false, 300, Dish.Type.FISH),
                new Dish("龙利鱼", false, 450, Dish.Type.FISH));


        //提取低于450卡路里的菜肴名称
        List<String> lowCaloricDishesName = menuList.stream()
                .filter(m -> m.getCalories() < 450) //卡路里小于450
                .sorted(Comparator.comparing(Dish::getCalories)) //根据卡路里从小到大排序
                .map(Dish::getName) //提取菜肴名称
                .collect(Collectors.toList());  //获取菜肴名称集合

        //遍历
        System.out.println("低卡路里的菜肴名称：");
        lowCaloricDishesName.forEach(System.out::println);


        //提取卡路里最多的三个菜肴名称
        List<String> threeHighCaloricDishNames = menuList.stream()
                .sorted(Comparator.comparingInt(Dish::getCalories).reversed()) //根据卡路里逆序排序
                .limit(3)
                .map(Dish::getName)
                .collect(Collectors.toList());

        menuList.stream()
                .mapToDouble(Dish::getCalories)
                .sum();

        menuList.stream()
                .mapToDouble(dish -> dish.getCalories())
                .count();


        //遍历
        System.out.println("卡路里最多的三个菜肴名称：");
        threeHighCaloricDishNames.forEach(System.out::println);

        // 流只能遍历一次
        Stream<Dish> stream = menuList.stream();
        stream.forEach(dish -> System.out.println(dish.getName() + ":" + dish.getCalories()));
        //再次遍历报错：java.lang.IllegalStateException
        //stream.forEach(dish -> System.out.println(dish.getName() + ":" + dish.getCalories()));
    }

}