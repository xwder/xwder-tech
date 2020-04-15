package com.xwder.newfeatures8.stream.eg.model;

/**
 * 一盘食物
 *
 * @author zifangsky
 * @date 2018/7/16
 * @since 1.0.0
 */
public class Dish {
    /**
     * 菜肴名称
     */
    private final String name;
    /**
     * 是否是素菜
     */
    private final boolean vegetarian;
    /**
     * 包含多少卡路里
     */
    private final int calories;
    /**
     * 类型
     */
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public enum Type {
        MEAT("肉"), FISH("鱼"), OTHER("其他");
        private String name;

        Type(String name) {
            this.name = name;
        }
    }
}
