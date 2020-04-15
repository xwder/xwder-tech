package com.xwder.designpatterns.decorator;

/**
 * 装饰器模式 测试
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class ClientTest {
	public static void main(String[] args) {
		Component component = new ConcreteComponent(); 
		System.out.println("------装饰前：-------");
		component.function();
		Component newComponent = new ConcreteDecorator(component);
		System.out.println("------装饰后：-------");
		newComponent.function();
	}
}
