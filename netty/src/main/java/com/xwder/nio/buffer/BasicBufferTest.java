package com.xwder.nio.buffer;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer demo
 *
 * @author xwder
 * @date 2021-03-15 23:35:37
 */
public class BasicBufferTest {
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


    /**
     * ByteBuffer支持类型化的put和get，放入put什么类型取出get什么数据类型
     */
    @Test
    public void byteBufferTest() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(100);
        byteBuffer.putLong(1000L);
        byteBuffer.putChar('地');
        byteBuffer.putShort((short) 4);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }

    /**
     * readOnlyBuffer
     */
    @Test
    public void readOnlyBufferTest() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.flip();
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        // ReadOnlyBufferException error
        readOnlyBuffer.put((byte) 100);
    }

    /**
     * MappedByteBuffer可以然文件直接在内存（堆外内存）修改，操作系统不需要再拷贝一次
     * @throws Exception
     */
    @Test
    public void mapperByteBufferTest() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * FileChannel.MapMode.READ_WRIT: 使用的读写模式
         * position: 可以直接修改的起始位置
         * size: 映射到内存的大小，从起始位置开始，即将1.txt的多少个字节映射到内存中
         * mappedByteBuffer实际类型是： DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');

        // IndexOutOfBoundException
        mappedByteBuffer.put(5, (byte) 'Y');

        randomAccessFile.close();
        System.out.printf("", "修改成功~~");
    }
}