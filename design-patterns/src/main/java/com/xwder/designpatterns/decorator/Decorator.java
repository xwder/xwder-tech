package com.xwder.designpatterns.decorator;

/**
 * 装饰器模式 具装饰角色
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class Decorator implements Component {

	//持有一个Component类型的对象引用
	private Component component;
	
	public Decorator(Component component) {
		this.component = component;
	}
 
	@Override
	public void function() {
		//客户端的调用委派给具体的子类
		component.function();
	}
	
}