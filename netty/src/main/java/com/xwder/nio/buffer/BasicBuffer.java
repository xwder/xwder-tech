package com.xwder.nio.buffer;

import java.nio.IntBuffer;

/**
 * IntBuffer demo
 *
 * @author xwder
 * @date 2021-03-15 23:35:37
 */
public class BasicBuffer {
    public static void main(String[] args) {

        //举例说明Buffer 的使用 (简单说明)
        //创建一个Buffer, 大小为 5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer 存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //将buffer转换，读写切换(!!!)
        intBuffer.flip();
        intBuffer.position(1);
        System.out.println(intBuffer.get());
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}