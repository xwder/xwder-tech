package com.xwder.newfeatures8.stream.eg;

import com.xwder.newfeatures8.stream.eg.model.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 映射、匹配、查找
 * <p>
 * 流支持map方法，它会接受一个函数作为参数。
 * 这个函数会被应用到每个元素上，并将其映射成一个新的元素
 * （使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）。
 * <p>
 * 另一个常见的数据处理操作是查看数据集中的某些元素是否匹配一个给定的属性。
 * Stream API通过allMatch、 anyMatch、 noneMatch、 findFirst和findAny方法提供了这样的工具。
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Demo4 {
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
     * 映射：使用flatMap方法，将流扁平化
     * 输出如下：
     * H e l o W r d
     */
    @Test
    public void testFlatMap() {
        //示例集合
        List<String> wordList = Arrays.asList("Hello", "World");

        List<String> uniqueCharacters = wordList.stream()
                .map(word -> word.split("")) //将每个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream) //将各个生成流扁平化为单个流
                .distinct()
                .collect(Collectors.toList());
        //遍历
        uniqueCharacters.forEach(s -> System.out.print(s + " "));
    }

    /**
     * 检查是否至少匹配一个元素
     * 输出如下：
     * 菜单中至少存在一个素菜
     */
    @Test
    public void testAnyMatch() {
        if (menuList.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("菜单中至少存在一个素菜");
        }
    }

    /**
     * 检查是否至少匹配一个元素
     * 输出如下：
     * 菜单中所有菜的热量都低于1000卡路里
     */
    @Test
    public void testAllMatch() {
        if (menuList.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("菜单中所有菜的热量都低于1000卡路里");
        }

        //或者使用noneMatch
        if (menuList.stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println("菜单中所有菜的热量都低于1000卡路里");
        }
    }

    /**
     * 使用findAny查找元素：不关心返回的元素是哪个
     * 输出如下：
     * Dish{name='薯条', vegetarian=true, calories=530, type=OTHER}
     */
    @Test
    public void testFindAny() {
        Optional<Dish> dish = menuList.stream()
                .filter(Dish::isVegetarian) //过滤出素菜
                .findAny();

        dish.ifPresent(System.out::println);
    }

    /**
     * 使用findFirst查找元素：找到第一个元素在并行上限制更多
     * 输出如下：
     * Dish{name='薯条', vegetarian=true, calories=530, type=OTHER}
     */
    @Test
    public void testFindFirst() {
        Optional<Dish> dish = menuList.stream()
                .filter(Dish::isVegetarian)
                .findFirst();

        dish.ifPresent(System.out::println);

        Dish dish1 = dish.get();

        menuList.stream().map(x -> x.getCalories() * 10).collect(Collectors.toList()).forEach(System.out::println);
    }

}
