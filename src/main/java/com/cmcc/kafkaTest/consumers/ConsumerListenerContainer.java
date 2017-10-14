package com.cmcc.kafkaTest.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
public class ConsumerListenerContainer extends KafkaMessageListenerContainer{

	@Autowired
	public ConsumerFactory consumerFactory;
	
	@Autowired
	public ContainerProperties containerProperties;
	
	@SuppressWarnings("unchecked")
	public ConsumerListenerContainer(ConsumerFactory consumerFactory, ContainerProperties containerProperties) {
//		this.consumerFactory = consumerFactory;
//		this.containerProperties = containerProperties;
		super(consumerFactory, containerProperties);
	}
	
	
	
	
	
	

}
