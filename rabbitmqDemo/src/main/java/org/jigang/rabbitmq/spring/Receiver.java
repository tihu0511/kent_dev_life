package org.jigang.rabbitmq.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class Receiver {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-rabbitmq-receiver.xml");
    }
}
