package com.at.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.195.131:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5、创建消息的消费
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true){
            // reveive()一直等待接收消息，在能够接收到消息之前将一直阻塞。是同步阻塞方式。和socket的accept方法类似的。
            // reveive(Long time) : 等待n毫秒之后还没有收到消息，就是结束阻塞。
            // 因为消息发送者是TextMessage，所以消息接受者也要是TextMessa
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if (null != textMessage){
                System.out.println("received:" + textMessage.getText());
            }else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
