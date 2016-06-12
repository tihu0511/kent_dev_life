package org.jigang.rabbitmq.workQueue;

import com.rabbitmq.client.*;
import org.jigang.rabbitmq.RabbitMQDict;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class Worker {
    public static void main(String[] args) throws IOException, TimeoutException {
        for (int i = 0; i < 2; i++) {
            Thread t = new WorkerThread();
            t.setName("Thread " + i);
            t.start();
        }
    }
}

class WorkerThread extends Thread {
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RabbitMQDict.HOST_IP);
            factory.setUsername(RabbitMQDict.USER_NAME);
            factory.setPassword(RabbitMQDict.PASSWORD);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //队列中消息持久化
            boolean durable = true;
            channel.queueDeclare(RabbitMQDict.TASK_QUEUE_NAME, durable, false, false, null);
            System.out.println(getName() + " Waiting for messages");

            //公平分发，不要同时给一个工作者超过一个任务，或者换句话说在一个工作者处理完成，发送确认之前不要给它分发一个新的消息。即把消息分发到下一个不繁忙的工作者
            //注意队列大小，如果你的所有工作者是在忙碌，你的队列就会被填满。可能要添加更多的工作者，或者有些其他策略
            channel.basicQos(1);

            QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
            //消息确认，告诉RabbitMQ这个消息已经被接受，处理完成，RabbitMQ 才可以删除它，保证worker死掉也不会丢失消息
            boolean autoAck = false;
            channel.basicConsume(RabbitMQDict.TASK_QUEUE_NAME, autoAck, queueingConsumer);

            while (true) {
                QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
                String message = new String(delivery.getBody(), RabbitMQDict.CHARSET);
                System.out.println(getName() + " received message : " + message);

                //模拟一个消息要处理3秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟其中一个worker异常死掉
                if ("Thread 1".equalsIgnoreCase(getName())) {
                    throw new NullPointerException();
                }

                System.out.println(getName() + " done!");

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
