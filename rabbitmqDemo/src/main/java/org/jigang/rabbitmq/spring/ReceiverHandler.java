package org.jigang.rabbitmq.spring;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class ReceiverHandler {
    public void handleMessage(String text) {
        System.out.println("Received: " + text);
    }
}
