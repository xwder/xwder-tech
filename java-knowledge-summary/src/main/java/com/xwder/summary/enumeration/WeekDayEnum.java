package com.xwder.summary.enumeration;

/**
 * 枚举对象 抽象类
 *
 * @author wande
 * @version 1.0
 * @date 2020/05/05
 */
public abstract class WeekDayEnum {
    private WeekDayEnum() {
    }

    public final static WeekDayEnum SUN = new WeekDayEnum() {

        @Override
        public WeekDayEnum nextDay() {
            return MON;
        }

    };
    public final static WeekDayEnum MON = new WeekDayEnum() {

        @Override
        public WeekDayEnum nextDay() {
            return SUN;
        }

    };

    public abstract WeekDayEnum nextDay();
	
/*	public WeekDayEnum nextDay(){
		if(this == SUN){
			return  MON;
		}else{
			return SUN;
		}
	}
*/

    @Override
    public String toString() {
        return this == SUN ? "SUN" : "MON";
    }
}
