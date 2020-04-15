package com.xwder.designpatterns.builder.one;


import com.xwder.designpatterns.builder.one.model.Robot;

/**
 * 建造者模式 机器人建造标准
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public interface IBuildRobot {

    void buildHead();

    void buildBody();

    void buildHand();

    void buildFoot();

    Robot createRobot();

}