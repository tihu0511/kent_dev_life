package org.jigang.rabbitmq;

import org.jigang.rabbitmq.helloworld.HelloWorldRecv;
import org.jigang.rabbitmq.helloworld.HelloWorldSender;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BF100271 on 2016/6/7.
 */
public class RabbitMQTest {
    @Test
    public void helloWorld() throws IOException, TimeoutException {
        HelloWorldSender sender = new HelloWorldSender();
        sender.send("Hello RabbitMQ!!");
        sender.closeConnection();
    }
}
