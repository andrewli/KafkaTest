package com.cmcc.kafkaTest.producers;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaProducerTest {
	
//	@Test
	public static  void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
        
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml"); 
		context.start();
		
		KafkaProducerServer kafkaProducer = (KafkaProducerServer) context.getBean("kafkaProducerServer");
		int i = 0;
		while(true){
			i += 1;
	        String topic = "orderTopic";
	        if(i%2!=0){
	        	topic = "otherOrderTopic";
	        }
	        String value = "test";
	        String ifPartition = "0";
	        Integer partitionNum = 3;
	        String role = "test";//用来生成key
	        Map<String,Object> res = kafkaProducer.sndMesForTemplate
	                (topic, value, ifPartition, partitionNum, role);
	        
	        System.out.println("测试结果如下：===============");
	        String message = (String)res.get("message");
	        String code = (String)res.get("code");
	        
	        System.out.println("code:"+code);
	        System.out.println("message:"+message);
	        
	        Thread.sleep(3000);
		}
    }

}
