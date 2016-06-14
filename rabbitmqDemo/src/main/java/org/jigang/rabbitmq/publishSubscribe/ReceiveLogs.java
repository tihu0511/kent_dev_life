package org.jigang.rabbitmq.publishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.jigang.rabbitmq.RabbitMQDict;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class ReceiveLogs {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Thread t = new RecvLogsThread();
            t.setName("Thread " + i);
            t.start();
        }
    }
}

class RecvLogsThread extends Thread {
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RabbitMQDict.HOST_IP);
            factory.setUsername(RabbitMQDict.USER_NAME);
            factory.setPassword(RabbitMQDict.PASSWORD);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(RabbitMQDict.LOGS_EXCHANGE, RabbitMQDict.EXCHANGE_TYPE_FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            //交换机和队列绑定，广播模式的routingKey为空
            channel.queueBind(queueName, RabbitMQDict.LOGS_EXCHANGE, "");
            System.out.println(getName() + " waiting for messages ");

            channel.basicQos(10);

            boolean autoAck = false;
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, autoAck, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody(), RabbitMQDict.CHARSET);

                System.out.println(getName() + " received message : " + message);
                Thread.sleep(3000);//模拟一个消息处理需要3秒
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
