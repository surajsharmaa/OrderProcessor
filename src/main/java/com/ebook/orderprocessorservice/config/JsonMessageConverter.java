package com.ebook.orderprocessorservice.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMessageConverter implements MessageConverter {
Class<?> valueType;
	
	public JsonMessageConverter(Class<?> valueType) {
		this.valueType = valueType; 
	}
	
	public Object fromMessage(Message message) throws JMSException {
		ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        Object object = null;
        try {
        	object = new ObjectMapper().readValue(textMessage.getText(), this.valueType);
        } catch (Exception jme) {
            throw new MessageConversionException("Error setting correlation Id in TextMessage", jme);
        }
        return object;
    }

    public Message toMessage(Object object, Session session) throws JMSException {
        TextMessage message = session.createTextMessage();
        try {
            String jsonString = new ObjectMapper().writeValueAsString(object);
            message.setText(jsonString);
        } catch (Exception jme) {
            throw new MessageConversionException("Error getting correlation Id in TextMessage", jme);
        }
        return message;
    }
	
}