package org.jigang.rabbitmq.spring.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class DirectSender {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq-producer.xml");
        AmqpTemplate amqpTemplate = (AmqpTemplate) context.getBean("rabbitTemplate");
        String[] routingKeys = {"r_tyb_direct_test1", "r_tyb_direct_test2", "r_tyb_direct_test3"};
        for(int i=0;i<100;i++){
            String routingKey = routingKeys[random.nextInt(routingKeys.length)];
            String message = "test spring async=>" + i;
            amqpTemplate.convertAndSend(routingKey, message);
            System.out.println("Send [routingKey=" + routingKey + "] message : " + message);
            Thread.sleep(500);
        }
    }

}
