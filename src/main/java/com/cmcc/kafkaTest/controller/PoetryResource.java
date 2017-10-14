package com.cmcc.kafkaTest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cmcc.kafkaTest.common.BaseAction;
import com.cmcc.kafkaTest.mybatis.dao.PoetryMapper;
import com.cmcc.kafkaTest.mybatis.entity.Poetry;


@RestController
public class PoetryResource extends  BaseAction{
	
	@Autowired
	public PoetryMapper poetryMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
    
    @RequestMapping(value="/getPoetryTitleFromCacheById/{poetryId}", method=RequestMethod.GET)
	public String getPoetryTitleFromCacheById(@PathVariable("poetryId") long poetryId){
		String key = "poetry." + poetryId;
		
		String title = stringRedisTemplate.opsForValue().get(key);
		if(StringUtils.isEmpty(title)){
			LOG.info("redis中没有要找的poetryTitle，设置poetryTitle");
			stringRedisTemplate.opsForValue().set(key, "设置新的redis值");
		}
		title = stringRedisTemplate.opsForValue().get(key);
		LOG.info(String.format("再次获取 poetryTitle: %s", title));
		return title;
	}
	
	@RequestMapping(value="/getPoetryContentById/{poetryId}", method=RequestMethod.GET)
	public String getPoetryContentById(@PathVariable("poetryId") long poetryId){
		LOG.info("getPoetryContentById param: "+ poetryId);
		
		String poetryContent = poetryMapper.getPoetryContentById(poetryId);
		
		return String.format("getPoetryContentById param:\n %s", poetryContent);
	}
	
	@RequestMapping(value="/getPoetryById/{poetryId}", method=RequestMethod.GET)
	public String getPoetryById(@PathVariable("poetryId") long poetryId){
		LOG.info("getPoetryById param: "+ poetryId);
		
		Poetry poetry = poetryMapper.getPoetryById(poetryId);
		
		String poetryStr = JSON.toJSONString(poetry);
		
		return String.format("getPoetryById param:\n %s", poetryStr);
	}
	
	

}
