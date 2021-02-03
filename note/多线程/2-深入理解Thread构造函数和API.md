## 一、深入理解Thread构造函数

### 1、线程的命名

### 2、线程的父子关系

### 3、Thread和ThreadGroup

### 4、守护线程

守护线程具备自动结束生命周期的特性；

当希望关闭某些线程的时候或者退出jvm进程的时候，一些线程能够自动关闭。

## 二、Thread API详细介绍

Thread类重点api：sleep、join、开启和Tan

### 1、线程sleep

```java
public static native void sleep(long millis) throws InterruptedException;

public static void sleep(long millis, int nanos)throws InterruptedException {
        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }
        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }
        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }
        sleep(millis);
    }
```

sleep方法会使当前线程禁入指定毫秒数的休眠，暂停执行，虽然指定休眠时间，但是最终要以系统的定时器和调度器的精度为准。

sleep休眠不会放弃 **monitor锁** 的所有权。

JdK1.5以后，建议可以使用**TimeUnit**替代Thread.sleep();

```java
public class TimeUnitTest {
    public static void main(String[] args) {
        try {
            TimeUnit.NANOSECONDS.sleep(1L);
            TimeUnit.SECONDS.sleep(1L);
            TimeUnit.HOURS.sleep(1L);
            TimeUnit.DAYS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### 2、线程yield

```Java
public static native void yield();
```

yield方法属于一种启发式的方法， 其会提醒调度器我愿意放弃当前的 CPU 资源， 如果CPU 的资源不紧张， 则会忽略这种提醒。

调用yield方法会使当前线程从 RUNNING 状态切换到 RUNNABLE 状态，一般这个方法不太常用。

### 3、sleep和yield的区别

- sleep会导致当前线程暂停指定的时间， 没有 CPU 时间片的消耗，sleep 会使线程短暂 block, 会在给定的时间内释放 CPU 资源；
- yield只是对 CPU 调度器的一个提示， 如果 CPU 调度器没有忽略这个提示， 它会导致线程上下文的切换；
- yield 会使 RUNNING 状态的 Thread 进入 RUNNABLE 状态（如果 CPU 调度器没有忽略这个提示的话）；
- sleep 几乎百分之百地完成了给定时间的休眠， 而 yield 的提示并不能一定担保；
- 一个线程 sleep 另一个线程调用 interrupt 会捕获到中断信号， 而 yield 则不会。

### 4、获取和设置线程优先级

```java
// 获取线程优先级
public final int getPriority() {
    return priority;
}
// 设置线程优先级
public final void setPriority(int newPriority) {
    ThreadGroup g;
    checkAccess();
    if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
        throw new IllegalArgumentException();
    }
    if((g = getThreadGroup()) != null) {
        if (newPriority > g.getMaxPriority()) {
            newPriority = g.getMaxPriority();
        }
        setPriority0(priority = newPriority);
    }
}
```

