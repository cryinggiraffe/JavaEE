package com.at.activemq.message;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.JmsException;

import javax.jms.*;
import java.io.IOException;

public class JmsBody_Consumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.195.131:61616";
    public static final String Topic_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {

        System.out.println("3");

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(Topic_NAME);

        MessageConsumer messageConsumer = session.createConsumer(topic);

        messageConsumer.setMessageListener((message) -> {
            if (null != message && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("received topic : " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != message && message instanceof MapMessage){
                MapMessage mapMessage = (MapMessage) message;
                try{
                    System.out.println("received topic : " + mapMessage.getString("name"));
                    System.out.println("received topic : " + mapMessage.getInt("age"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        });

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
