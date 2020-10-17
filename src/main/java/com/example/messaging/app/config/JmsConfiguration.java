package com.example.messaging.app.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;


@Configuration
public class JmsConfiguration {

	private static final String BROKER_URL = "tcp://127.0.0.1:61616";
	private static final String QUEUE = "messaging_app_queue";


	@Bean
	public ConnectionFactory connectionFactory()
	{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("com.example.messaging.app"));
		return connectionFactory;
	}

	
	@Bean
	public JmsTemplate jmsTemplate()
	{
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(QUEUE);
		return template;
	}

	@Bean
	MessageConverter converter()
	{
		return new SimpleMessageConverter();
	}
}
