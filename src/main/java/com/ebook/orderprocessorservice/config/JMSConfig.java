package com.ebook.orderprocessorservice.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.ebook.api.message.Email;

@Configuration
public class JMSConfig {
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Bean
	public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);

		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory(senderActiveMQConnectionFactory());
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		//return new JmsTemplate(cachingConnectionFactory());
		JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(cachingConnectionFactory());
	    template.setMessageConverter(messageConverter());
	    return template;
	}
	
	@Bean // Serialize message content to json using TextMessage
	  public MessageConverter messageConverter() {
	    return new JsonMessageConverter(Email.class);
	  }
}
