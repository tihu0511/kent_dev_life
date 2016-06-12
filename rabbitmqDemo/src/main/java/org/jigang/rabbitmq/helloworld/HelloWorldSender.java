package org.jigang.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jigang.rabbitmq.RabbitMQDict;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BF100271 on 2016/6/7.
 */
public class HelloWorldSender {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message) throws IOException, TimeoutException {
        channel.queueDeclare(RabbitMQDict.QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", RabbitMQDict.QUEUE_NAME, null, message.getBytes());
        System.out.println(RabbitMQDict.HOST_IP + " sent \"" + message + "\"");

    }

    public void closeConnection() throws IOException, TimeoutException {
        if (null != channel) {
            channel.close();
        }
        if (null != connection) {
            connection.close();
        }
    }

    public static void main(String [] args) throws IOException, TimeoutException {
        HelloWorldSender sender = new HelloWorldSender();
        for (int i = 0; i < 100; i++) {
            sender.send("Hello RabbitMQ!! " + i);
        }
        sender.closeConnection();
    }
}
