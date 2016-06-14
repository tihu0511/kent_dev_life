package org.jigang.rabbitmq.spring.fanout;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by BF100271 on 2016/6/13.
 */
public class FanoutConsumerList1 implements MessageListener {
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("fanout consumer 1 received : " + msg);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
