package org.jigang.rabbitmq.spring.direct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by BF100271 on 2016/6/12.
 */
public class ConsumerListener3 implements MessageListener {
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("consumer 3 received : " + msg);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
