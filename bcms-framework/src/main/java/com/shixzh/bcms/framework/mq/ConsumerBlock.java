package com.shixzh.bcms.framework.mq;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 *
 * @author ctk
 */
public class ConsumerBlock implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEP_TIME = 1000;

    public ConsumerBlock(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id :" + Thread.currentThread().getId());
        Random random = new Random();
        try {
            while (true) {
                PCData data = queue.take(); // 取出队列头部元素
                if (data != null) {
                    int re = data.getData() * data.getData();
                    System.out.println(MessageFormat.format("{0} * {1} = {2}", data.getData(), data.getData(), re));
                    Thread.sleep(random.nextInt(SLEEP_TIME)); // 睡眠随机事件0-1s
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}