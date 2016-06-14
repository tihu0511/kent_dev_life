package org.jigang.rabbitmq.spring.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * Created by BF100271 on 2016/6/13.
 */
public class FanoutSender {
    private static Random random = new Random();

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq-producer.xml");
        AmqpTemplate amqpTemplate = (AmqpTemplate) context.getBean("rabbitFanoutTemplate");
        for(int i=0;i<100;i++){
            String message = "test spring async fanout =>" + i;
            amqpTemplate.convertAndSend(message);
            System.out.println("Send message : " + message);
            Thread.sleep(500);
        }
    }
}
