package org.jigang.rabbitmq.spring.fanout;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BF100271 on 2016/6/14.
 */
public class FanoutReceiver {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-rabbitmq-consumer1.xml");
    }
}
