package com.xwder.newfeatures8.stream.eg;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 归约
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Demo5 {
    //示例集合
    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4);


    /**
     * 使用reduce归约元素
     * 输出如下：
     * 10
     * <p>
     * reduce接受两个参数：
     * 一个初始值
     * 一个Lambda来把两个流元素结合起来并产生一个新值
     */
    @Test
    public void testReduce() {
        int sum = numbers.parallelStream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(sum);
    }

    /**
     * 使用reduce归约元素，考虑到流中没有任何元素的情况，可以不接受初始值但是会返回一个Optional对象
     * 输出如下：
     * 10
     */
    @Test
    public void testReduceOptional() {
        Optional<Integer> sum = numbers.parallelStream()
//                .reduce((a, b) -> a + b)
                .reduce(Integer::sum);

        sum.ifPresent(System.out::println);
    }

    /**
     * 使用reduce求最大或最小元素
     * 输出如下：
     * 最大值：4
     * 最小值：1
     */
    @Test
    public void testReduceMax() {
        Optional<Integer> max = numbers.parallelStream()
                .reduce(Integer::max);
        Optional<Integer> min = numbers.parallelStream()
                .reduce(Integer::min);

        max.ifPresent(m -> System.out.println("最大值：" + m));
        min.ifPresent(m -> System.out.println("最小值：" + m));
    }

    /**
     * 测试同时使用Map和Reduce
     * 输出如下：
     * 4
     */
    @Test
    public void testMapReduce() {
        int count = numbers.parallelStream()
                .map(m -> 1) //把每个元素映射为1
                .reduce(0, Integer::sum); //求和

        System.out.println(count);

        numbers.stream().map(x -> x * 1000).collect(Collectors.toList()).forEach((System.out::println));
    }

}
