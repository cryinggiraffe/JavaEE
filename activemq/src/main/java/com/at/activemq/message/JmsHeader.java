package com.at.activemq.message;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsHeader {

    public static final String ACTIVEMQ_URL = "tcp://192.168.195.131:61616";
    public static final String Topic_NAME = "topic01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(Topic_NAME);
        MessageProducer messageProducer = session.createProducer(topic);

        for (int i = 1; i <= 3; i++){
            TextMessage textMessage = session.createTextMessage("topic_name--" + i);

            textMessage.setJMSDestination(topic);

            textMessage.setJMSDeliveryMode(0);

            textMessage.setJMSExpiration(1000);

            textMessage.setJMSPriority(10);

            textMessage.setJMSMessageID("abcd");

            messageProducer.send(textMessage);
        }

        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("done");

    }
}
