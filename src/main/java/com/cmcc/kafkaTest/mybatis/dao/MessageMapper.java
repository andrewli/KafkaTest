package com.cmcc.kafkaTest.mybatis.dao;

import org.apache.ibatis.annotations.Select;

public interface MessageMapper {
	
	
	@Select("SELECT message FROM t_message ORDER BY id DESC LIMIT 1")
	String getLatestMessageInTopic(String topic);

}
