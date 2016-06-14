package org.jigang.rabbitmq.spring.direct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class DirectReceiver1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq-consumer1.xml");
    }
}
