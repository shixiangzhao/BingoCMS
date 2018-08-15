package com.shixzh.bcms.framework.mq;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducterAndConsumerTest {
    @Test
    public void testProducterAndConsumer() throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        ProducerBlock p1 = new ProducerBlock(queue);
        ProducerBlock p2 = new ProducerBlock(queue);
        ProducerBlock p3 = new ProducerBlock(queue);
        ConsumerBlock c1 = new ConsumerBlock(queue);
        ConsumerBlock c2 = new ConsumerBlock(queue);
        ConsumerBlock c3 = new ConsumerBlock(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        Thread.sleep(10 * 1000);
        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}
