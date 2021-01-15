package com.xwder.newfeatures8.stream.eg;

import org.junit.Test;

import java.util.List;
import java.util.logging.XMLFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流与构建流
 * <p>
 * Java 8引入了三个原始类型特化流接口（IntStream、 DoubleStream和LongStream），
 * 分别用于将流中的元素特化为int、 long和double，从而避免了暗含的装箱成本，这也就是数值流。
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Demo5 {

    /**
     * 构建数值流
     * 输出如下：
     * 2
     * 4
     * 6
     * 8
     * 10
     * 3, 4, 5
     * 5, 12, 13
     * 6, 8, 10
     * 7, 24, 25
     * 8, 15, 17
     */
    @Test
    public void testIntStream() {
        //生成偶数
        IntStream evenNumbers = IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0);

        evenNumbers.forEach(System.out::println);

        //生成勾股数 //外层生成0-100的自然数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                //内层再次生成a-100的自然数
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        //筛选符合勾股定理
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        //构建勾股数
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    /**
     * 几种构建流的方式
     * 输出如下：
     * Java 8
     * Lambdas
     * In
     * Action
     */
    @Test
    public void testCreateStream() {
        //字符串流
        Stream<String> stringStream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        //空流
        Stream<String> emptyStream = Stream.empty();

        stringStream.forEach(System.out::println);
    }

    /**
     * 通过迭代生成流
     * 输出如下：
     * 1
     * 3
     * 5
     * 7
     * 9
     */
    @Test
    public void testIterate() {
        Stream.<Integer>iterate(1, n -> n + 2).limit(5)
                .forEach(System.out::println);
    }

    /**
     * 通过generate方法生成流，与iterate方法类似，
     * 但是generate方法不是依次对每个新生成的值应用函数，而是接受一个Supplier<T>类型的Lambda提供新的值
     * 输出如下：
     * 0.8761217090781523
     * 0.2723966370610904
     * 0.0018463146970718602
     * 0.6037673908012483
     * 0.5766660405584716
     */
    @Test
    public void testGenerate() {
        Stream.<Double>generate(Math::random).limit(5)
                .forEach(System.out::println);

        Stream.<Double>generate(Math::random).limit(5)
                .map(x -> x * 10).collect(Collectors.toList()).forEach(System.out::println);
    }


}