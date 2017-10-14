package com.cmcc.kafkaTest.consumers;

import java.util.HashMap;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;


/**使用注解实现kafka consumer 消费信息**/
public class KafkaConsumerTest {
	
	public static void main(String[] args){
		Logger LOG = Logger.getLogger(KafkaConsumerTest.class);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml"); 
		context.start();
		LOG.info("start consumer test");
		HashMap<String, Object> configMap = new HashMap<String, Object>();
//		configMap.put("bootstrap.servers", "localhost:9091");
//		configMap.put("group.id", "0");
//		configMap.put("retries", "1");
//		configMap.put("batch.size", "16384");
//		configMap.put("linger.ms", "1");
//		configMap.put("buffer.memory", "1024000");
		
		configMap.put("bootstrap.servers", "localhost:9091");
		configMap.put("group.id", "2");
		configMap.put("enable.auto.commit", "false");
		configMap.put("auto.commit.interval.ms", "1000");
		configMap.put("session.timeout.ms", "15000");
		
		ConsumerConfig config = new ConsumerConfig();
		config.setConfigMap(configMap);
		
		ConsumerFactory factory = new ConsumerFactory(config.getConfigMap(), new StringDeserializer(),new StringDeserializer());
		
		KafkaConsumerListenerServer messageListener = (KafkaConsumerListenerServer) context.getBean("consumerMessageListerner");
		
		ContainerProperties orderTopicContainerProperties = new ContainerProperties("orderTopic");
		orderTopicContainerProperties.setMessageListener(messageListener);
		orderTopicContainerProperties.setAckMode(AckMode.BATCH);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		KafkaMessageListenerContainer messageListenreContainer = new KafkaMessageListenerContainer(factory, orderTopicContainerProperties);
		messageListenreContainer.start();
		
		/*
		ContainerProperties otherOrderTopicContainerProperties = new ContainerProperties("otherOrderTopic");
		otherOrderTopicContainerProperties.setMessageListener(messageListener);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		KafkaMessageListenerContainer otherMessageListenreContainer = new KafkaMessageListenerContainer(factory, otherOrderTopicContainerProperties);
		otherMessageListenreContainer.start();
		*/
		
		
	}

}
