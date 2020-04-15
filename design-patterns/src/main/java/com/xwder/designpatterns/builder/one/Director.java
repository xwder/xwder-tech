package com.xwder.designpatterns.builder.one;


import com.xwder.designpatterns.builder.one.IBuildRobot;
import com.xwder.designpatterns.builder.one.model.Robot;

/**
 * 建造者模式 指挥官Director
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class Director {
    public Robot createRobotByDirecotr(IBuildRobot ibr) {
        ibr.buildBody();
        ibr.buildFoot();
        ibr.buildHand();
        ibr.buildHead();
        return ibr.createRobot();
    }
}