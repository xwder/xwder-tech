package com.xwder.newfeatures8.optional;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * optional
 *
 * https://www.cnblogs.com/zhangboyu/p/7580262.html
 *
 * @author wande
 * @version 1.0
 * @date 2020/08/03
 */
public class OptionalExampleTest {

    /**
     * Optional.empty()
     * 创建 Optional实例 空的
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<Object> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**
     * Optional.of(obj)、Optional.ofNullable(obj)
     * 创建 Optional实例
     * null 值作为参数传递进去，of() 方法会抛出 NullPointerException
     * 明确对象不为 null  的时候使用 of()
     * 对象即可能是 null 也可能是非 null，应该使用 ofNullable()
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        Object obj = null;

        Optional<Object> opt = Optional.of(obj);

        // 可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法
        Optional<Object> optional = Optional.ofNullable(obj);
    }

    /**
     * 访问 Optional 对象的值
     * 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法
     */
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "xwder";
        Optional<String> opt = Optional.ofNullable(name);
        // get() 这个方法会在值为 null 的时候抛出异常
        assertEquals("xwder", opt.get());
    }

    /**
     * 检查是否有值的  isPresent()、ifPresent()
     */
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("xwder.com@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());
        assertEquals(user.getEmail(), opt.get().getEmail());
        // 检查是否有值的另一个选择是 ifPresent() 方法。该方法除了执行检查，
        // 还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式
        opt.ifPresent(u -> assertEquals(user.getEmail(), u.getEmail()));
    }

    /**
     * 返回默认值 orElse()、orElseGet()
     * orElse()：有值则返回该值，否则返回传递给它的参数值
     * orElseGet()：会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果
     */
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());

        User user3 = new User("anna@gmail.com", "1234");
        User result2 = Optional.ofNullable(user2).orElse(user3);
        // 对象的初始值不是 null，那么默认值会被忽略
        assertEquals("anna@gmail.com", result.getEmail());

        User result3 = Optional.ofNullable(user).orElseGet(() -> user2);

    }

    /**
     * orElse()、orElseGet() 区别
     * Optional 对象不为空时他们的行为不一致, erElse会行 else方法, orElseGet()不会执行 else 方法
     */
    @Test
    public void orElseAndOrElseGetDiffrent() {
        // Optional 对象为空时他们的行为一致 都会执行 else 方法。
        User user = null;
        System.out.println("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());

        // Optional 对象不为空时他们的行为不一致, erElse会行 else方法, orElseGet()不会执行 else 方法。
        User user2 = new User("john@gmail.com", "1234");
        System.out.println("Using orElse");
        User result3 = Optional.ofNullable(user2).orElse(createNewUser());
        System.out.println("Using orElseGet");
        User result4 = Optional.ofNullable(user2).orElseGet(() -> createNewUser());
    }

    private User createNewUser() {
        System.out.println("Creating New User");
        return new User("extra@gmail.com", "1234");
    }

    /**
     * 返回异常 orElseThrow()
     * 对象为空的时候抛出异常，而不是返回备选的值
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    /**
     * 转换值 map(),flatMap()
     * map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中
     * flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果
     */
    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        assertEquals(email, user.getEmail());

        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");

        assertEquals(position, user.getPosition().get());
    }

    /**
     * 过滤值 filter()
     * filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。
     * 如果测试结果为 false，会返回一个空的 Optional。
     */
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

        assertTrue(result.isPresent());
    }

    /**
     * Optional 类的链式方法
     */
    @Test
    public void whenChaining_thenOk() {
        User user = new User("anna@gmail.com", "1234");

        // 这样调用可能会抛出空指针异常
        //String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();


        String result = Optional.ofNullable(user)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getIsocode())
                .orElse("default");

        assertEquals(result, "default");

        //方法引用 精简
        result = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getIsocode)
                .orElse("default");

    }

    /**
     * Java 9 增强
     */
    @Test
    public void whenEmptyOptional_thenGetValueFromOr() {
        User user = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user)
                .or(() -> Optional.of(new User("default", "1234"))).get();

        assertEquals(result.getEmail(), "anna@gmail.com");

        Optional.ofNullable(user).ifPresentOrElse(u -> System.out.println("User is:" + u.getEmail()),
                () -> System.out.println("User not found"));
    }

    /**
     * 通过把实例转换为 Stream 对象
     */
    @Test
    public void whenGetStream_thenOk() {
        User user = new User("john@gmail.com", "1234");
        List<String> emails = Optional.ofNullable(user)
                .stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"))
                .map(u -> u.getEmail())
                .collect(Collectors.toList());

        assertTrue(emails.size() == 1);
        assertEquals(emails.get(0), user.getEmail());
    }

    /**
     * Optional 使用
     * Optional 类有一个非常有用的用例，就是将其与流或其它返回 Optional 的方法结合，以构建流畅的API
     */
    @Test
    public void whenEmptyStream_thenReturnDefaultOptional() {
        List<User> users = new ArrayList<>();
        User user = users.stream().findFirst().orElse(new User("default", "1234"));

        assertEquals(user.getEmail(), "default");
    }
}

/**
 * 重构类，使其 getter 方法返回 Optional 引用
 */
@Data
class User {
    String email;
    String pwd;
    String position;
    private Address address;

    public User() {
    }

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

@Data
class Address {
    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }
}

@Data
class Country {
    private String isocode;
}