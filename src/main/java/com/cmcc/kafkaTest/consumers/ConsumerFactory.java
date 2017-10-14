package com.cmcc.kafkaTest.consumers;

import java.util.HashMap;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@SuppressWarnings("rawtypes")
public class ConsumerFactory extends DefaultKafkaConsumerFactory{

	public ConsumerConfig consumerConfig;
	
	public ConsumerConfig getConsumerConfig() {
		return consumerConfig;
	}

	public void setConsumerConfig(ConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}

	@SuppressWarnings("unchecked")
	public ConsumerFactory(HashMap<String, Object> consumerConfig, Deserializer keyDeserializer, Deserializer valueDeserializer) {
		super(consumerConfig, keyDeserializer, valueDeserializer);
	}
	
	

}
