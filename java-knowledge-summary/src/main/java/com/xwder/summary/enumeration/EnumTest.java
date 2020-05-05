package com.xwder.summary.enumeration;

import org.junit.Test;

import java.util.Date;

/**
 * @author wande
 * @version 1.0
 * @date 2020/05/05
 */
public class EnumTest {

    @Test
    public void enumTest1(){
        WeekDayEnum weekDay = WeekDayEnum.MON;
        System.out.println(weekDay.nextDay());

        WeekDayInnerEnum weekDay2 = WeekDayInnerEnum.FRI;
        System.out.println(weekDay2);
        System.out.println(weekDay2.name());
        System.out.println(weekDay2.ordinal());

        //******************************
        System.out.println(WeekDayInnerEnum.SAT.ordinal());
        System.out.println(WeekDayInnerEnum.valueOf("SUN").toString());
        System.out.println(WeekDayInnerEnum.values().length);
        //******************************

        Date date = new Date(300) {
        };
        System.out.println(date);
    }


    public enum WeekDayInnerEnum {

        SUN(1), MON(), TUE, WED, THI, FRI, SAT;

        WeekDayInnerEnum() {
            System.out.println("first");
        }

        WeekDayInnerEnum(int day) {
            System.out.println("second");
        }
    }

    public enum TrafficLampEnum {
        RED(30) {
            @Override
            public TrafficLampEnum nextLamp() {
                return GREEN;
            }
        },
        GREEN(45) {
            @Override
            public TrafficLampEnum nextLamp() {
                return YELLOW;
            }
        },
        YELLOW(5) {
            @Override
            public TrafficLampEnum nextLamp() {
                return RED;
            }
        };

        public abstract TrafficLampEnum nextLamp();

        @SuppressWarnings("unused")
        private int time;

        TrafficLampEnum(int time) {
            this.time = time;
        }
    }
}
