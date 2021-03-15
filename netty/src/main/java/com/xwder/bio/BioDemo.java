package com.xwder.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试bio 测试方法运行main，然后[telnet 127.0.0.1 6666] 然后 ctrl+] ,使用 [send 内容] 命令发送内容
 *
 * @Author: xwder
 * @Description:
 * @Date: 2021/3/15 21 54
 */
public class BioDemo {
    /**
     * 核心线程池大小
     */
    public static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程池大小
     */
    public static final int MAX_POOL_SIZE = 10;
    /**
     * 阻塞任务队列大小
     */
    public static final int QUEUE_CAPACITY = 100;
    /**
     * 空闲线程存活时间
     */
    public static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws IOException {

        // 1.创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 2.创建一个ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动~~");

        while (true) {
            final Socket accept = serverSocket.accept();
            System.out.println("当前线程名称:" + Thread.currentThread().getName() + " 建立一个连接~~");
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }


    /**
     * 和客户端通讯的handler方法
     *
     * @param socket
     */
    public static void handler(Socket socket) {
        try {
            System.out.println("handler当前线程名称:" + Thread.currentThread().getName() + " handler()~~");

            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            // 循环读取客户端发送的数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println("handler当前线程名称:" + Thread.currentThread().getName() + "读取到内容:" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (Exception exception) {
                e.printStackTrace();
            }
        }


    }
}
