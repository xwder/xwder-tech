package com.xwder.designpatterns.builder.one;

import com.xwder.designpatterns.builder.one.model.Robot;

/**
 * 建造者模式 方式一 测试
 * 来源 参考 https://www.cnblogs.com/luohanguo/p/10455745.html
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class BuilderTest {
    public static void main(String[] args) {
        Director director = new Director();
        Robot robot = director.createRobotByDirecotr(new DanceRobotBuilder());
        System.out.println(robot.getHead());
        System.out.println(robot.getBody());
        System.out.println(robot.getHand());
        System.out.println(robot.getFoot());
        System.out.println("机器人创建成功，恭喜！");
    }
}