package com.xwder.nio.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * FileChannel demo
 *
 * @author xwder
 * @date 2021/3/16 20:57
 */
public class FileChannelDemoTest {

    /**
     * 通过fileChannel将字符串写入文件
     *
     * @throws Exception
     */
    @Test
    public void writeFileWithFileChannel() throws Exception {
        String str = "hello fileChannel";
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");

        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str放入byteBuffer
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        // 翻转byteBuffer
        byteBuffer.flip();

        // 将byteBuffer写入到 fileChannel
        fileOutputStreamChannel.write(byteBuffer);
        fileOutputStream.close();

    }

    /**
     * 通过fileChannel读取字符输出
     *
     * @throws Exception
     */
    @Test
    public void readFileWithFileChannel() throws Exception {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel = fileInputStream.getChannel();

        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int readLength = channel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();

    }

    /**
     * 通过fileChannel读写文件
     *
     * @throws Exception
     */
    @Test
    public void readAndWriteFileWithFileChannel() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(512);

        while (true) {
            // 这里要clear()
            allocate.clear();
            int read = inputStreamChannel.read(allocate);
            if (read == -1) {
                break;
            }
            allocate.flip();
            outputStreamChannel.write(allocate);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * transferFrom拷贝文件
     *
     * @throws Exception
     */
    @Test
    public void transferFromTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("waliefu.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("jqrzdy.jpg");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        inputStreamChannel.close();
        outputStreamChannel.close();

        fileInputStream.close();
        fileOutputStream.close();

    }

}
