package com.xwder.designpatterns.proxy.staticproxy;

/**
 * 静态代理<p>
 * 静态代理总结:<p>
 * 1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.<p>
 * 2.缺点: *
 * 因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.
 * 同时,一旦接口增加方法,目标对象与代理对象都要维护.
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class PersonDaoProxy implements IPersonDao {
    private IPersonDao target;

    public PersonDaoProxy(IPersonDao target) {
        this.target = target;
    }

    @Override
    public void update() {
        System.out.println("修改个人信息前记录日志");
        this.target.update();
        System.out.println("修改个人信息后记录日志");
    }

}
