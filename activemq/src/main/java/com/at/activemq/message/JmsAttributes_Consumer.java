package com.at.activemq.message;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsAttributes_Consumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.195.131:61616";
    public static final String Topic_NAME = "topic01";

    public static void main(String[] args) throws JMSException {

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
                    System.out.println("消息体："+textMessage.getText());
                    System.out.println("消息属性："+textMessage.getStringProperty("From"));
                    System.out.println("消息属性："+textMessage.getByteProperty("Spec"));
                    System.out.println("消息属性："+textMessage.getBooleanProperty("Invalide"));
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
