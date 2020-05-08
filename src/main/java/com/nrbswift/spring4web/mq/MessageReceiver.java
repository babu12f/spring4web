package com.nrbswift.spring4web.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class MessageReceiver implements MessageListener {
//    @Autowired
//    JmsTemplate jmsTemplate;

    @Autowired
    MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            Product product = (Product) messageConverter.fromMessage(message);
            System.out.println("======================== Message Receive ===========================");
            System.out.println(product);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

//    public String receiveMessage() {
//        try {
//            Message message = jmsTemplate.receive();
//            String response = (String) messageConverter.fromMessage(message);
//            return response;
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Product receiveObjectMessage() {
//        try {
//            Message message = jmsTemplate.receive();
//            Product product = (Product) messageConverter.fromMessage(message);
//            return product;
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
