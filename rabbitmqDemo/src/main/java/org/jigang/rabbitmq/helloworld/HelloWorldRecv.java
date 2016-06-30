package org.jigang.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import org.jigang.rabbitmq.RabbitMQDict;

import java.io.IOException;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class HelloWorldRecv {
    private static Connection connection = null;
    private static Channel channel = null;


    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMQDict.HOST_IP);
        factory.setUsername(RabbitMQDict.USER_NAME);
        factory.setPassword(RabbitMQDict.PASSWORD);
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(RabbitMQDict.QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Waiting for messages.");
    }

    public static void main(String[] args) throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, RabbitMQDict.CHARSET);
                System.out.println("Received message : " + message);
            }
        };
        channel.basicConsume(RabbitMQDict.QUEUE_NAME, true, consumer);
    }
}
