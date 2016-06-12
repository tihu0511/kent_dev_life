package org.jigang.rabbitmq.publishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jigang.rabbitmq.RabbitMQDict;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class EmitLogs {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMQDict.HOST_IP);
        factory.setUsername(RabbitMQDict.USER_NAME);
        factory.setPassword(RabbitMQDict.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(RabbitMQDict.LOGS_EXCHANGE, RabbitMQDict.EXCHANGE_TYPE_FANOUT);

        for (int i = 0; i < 5; i++) {
            String message = "MSG " + i;
            channel.basicPublish(RabbitMQDict.LOGS_EXCHANGE, "", null, message.getBytes());
            System.out.println("Send message : " + message);

            //模拟隔1秒发送一条消息
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        channel.close();
        connection.close();
    }
}
