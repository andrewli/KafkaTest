package com.cmcc.kafkaTest;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@MapperScan("com.cmcc.kafkaTest.mybatis.dao")
public class KafkaTestApplication {    
	
	public static Logger LOG = Logger.getLogger("KafkaTestApplication"); 
	public static ApplicationContext applicationContext;
    
	public static void main(String[] args){
		applicationContext = SpringApplication.run(KafkaTestApplication.class, args);
		LOG.info("Started kafkaTestApplication server");
		
		
	}

}
