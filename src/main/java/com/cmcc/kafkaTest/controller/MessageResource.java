package com.cmcc.kafkaTest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmcc.kafkaTest.common.BaseAction;


@RestController
public class MessageResource extends BaseAction{
	
	@RequestMapping(value="/getLastestMsgInTopic/{topic}", method=RequestMethod.GET)
	public String getLastestMsgInTopic(@PathVariable("topic") String topic){
		LOG.info("getLastestMsgInTopic param: "+ topic);
		
		
		
		return String.format("getLastestMsgInTopic param: %s", topic);
	}
	
	@RequestMapping(value="/sendMsgToTopic/{topic}", method=RequestMethod.POST)
	public String sendMsgToTopic(@PathVariable("topic") String topic, @RequestParam("message") String message){
		LOG.info(String.format("sendMsgToTopic path: %s and param: %s", topic, message));
		
		
		
		return String.format("sendMsgToTopic path: %s and param: %s", topic, message);
	}

}
