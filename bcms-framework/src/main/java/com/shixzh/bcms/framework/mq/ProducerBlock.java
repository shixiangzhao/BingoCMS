package com.shixzh.bcms.framework.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 * @author ctk
 * 生产者消费者模型
 */
public class ProducerBlock implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;// 内存缓冲区
    private static AtomicInteger count = new AtomicInteger();// 总数 原子操作
    private static final int SLEEP_TIME = 1000;

    public ProducerBlock(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data;
        Random random = new Random();
        System.out.println("start producting id:" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEP_TIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data + " 加入队列");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println(" 加入队列失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }

    public void stop() {
        isRunning = false;
    }
}