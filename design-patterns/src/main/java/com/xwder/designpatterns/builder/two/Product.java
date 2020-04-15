package com.xwder.designpatterns.builder.two;

/**
 * 建造者模式 静态内部类方式
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class Product {

    private final int id;
    private final String name;
    private final int type;
    private final float price;

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.price = builder.price;
    }

    public static class Builder {
        private int id;
        private String name;
        private int type;
        private float price;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder price(float price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

}