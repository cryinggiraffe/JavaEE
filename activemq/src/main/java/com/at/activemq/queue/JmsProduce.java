package com.at.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {

    public static final String ACTIVEMQ_URL = "tcp://192.168.195.131:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂,按照给定URL，采用默认账户和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        //2、通过连接工厂，获得Connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 3、创建会话session
        // 第一参数是是否开启事务，第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        //4、创建目的地（两种：队列/主题）。Destination是Queue和Topic的父类
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的生产者，目的地为队列
        MessageProducer messageProducer = session.createProducer(queue);
        // 6 通过messageProducer 生产3 条消息发送到消息队列
        for (int i = 1; i <= 3; i++){
            // 7  创建消息
            TextMessage textMessage = session.createTextMessage("msg-listener-" + i);
            // 8  通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }
        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("done");
    }
}
