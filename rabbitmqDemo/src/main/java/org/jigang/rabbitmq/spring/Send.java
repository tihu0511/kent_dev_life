package org.jigang.rabbitmq.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class Send {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq-sender.xml");
        AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);
        for(int i=0;i<1000;i++){
            amqpTemplate.convertAndSend("test spring async=>"+i);
            Thread.sleep(1000);
        }
    }
}
