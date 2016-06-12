package org.jigang.rabbitmq;

/**
 * Created by BF100271 on 2016/6/7.
 */
public interface RabbitMQDict {
    String HOST_IP = "192.168.153.128";
    String QUEUE_NAME = "hello";
    String USER_NAME = "kent";
    String PASSWORD = "kent";

    String CHARSET = "utf-8";

    String TASK_QUEUE_NAME = "task_queue";
    String LOGS_EXCHANGE = "logs";
    java.lang.String EXCHANGE_TYPE_FANOUT = "fanout";
}
