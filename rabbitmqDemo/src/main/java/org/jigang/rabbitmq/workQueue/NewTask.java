package org.jigang.rabbitmq.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.jigang.rabbitmq.RabbitMQDict;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class NewTask {


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMQDict.HOST_IP);
        factory.setUsername(RabbitMQDict.USER_NAME);
        factory.setPassword(RabbitMQDict.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //使用队列持久化
        boolean durable = true;
        channel.queueDeclare(RabbitMQDict.TASK_QUEUE_NAME, durable, false, false, null);

        //1秒发一条消息
        for (int i = 0; i < 5; i++) {
            String message = "Msg " + i;

            channel.basicPublish("", RabbitMQDict.TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("Send message : " + message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        channel.close();
//        connection.close();
    }
}
