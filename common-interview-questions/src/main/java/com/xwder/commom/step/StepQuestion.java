package com.xwder.commom.step;

/**
 * 编程题：有n步台阶，一次只能上1步或2步，共有多少种走法？
 *
 * @Author: xwder
 * @Date: 2019/10/15 19:29
 * @Description:
 */
public class StepQuestion {

    public static void main(String[] args) {
        StepQuestion stepQuestion = new StepQuestion();
        long startTime = System.currentTimeMillis();
        long result = stepQuestion.calc1(40);
        System.out.println(result + "  " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        result = stepQuestion.calc2(40);
        System.out.println(result + "  " + (System.currentTimeMillis() - startTime));
    }

    /**
     * 通过递归处理
     * @param n n步台阶
     * @return
     */
    public long calc1(int n) {
        if(n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n==1 || n==2){
            return n;
        }
        return calc1(n - 2) + calc1(n - 1);
    }

    /**
     * 迭代的方法计算
     * @param n n步台阶
     * @return
     */
    public long calc2(int n){
        if(n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n==1 || n==2){
            return n;
        }

        //初始化为走到第二级台阶的走法
        long one = 2L;
        //初始化为走到第一级台阶的走法
        long two = 1L;
        long sum = 0L;

            for(int i=3; i<=n; i++){
                //最后跨2步 + 最后跨1步的走法
                sum = two + one;
                two = one;
                one = sum;
            }
            return sum;
    }

}
