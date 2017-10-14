package com.cmcc.kafkaTest.mybatis.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cmcc.kafkaTest.mybatis.entity.Poetry;

public interface PoetryMapper {
	
	
	@Select("SELECT * FROM t_poetry WHERE id=#{poetryId}")
	@Results({
		@Result(property="poetryId", column="poetry_id"),
		@Result(property="poetryName", column="poetry_name"),
		@Result(property="authorName", column="author_id"),
		@Result(property="authorId", column="author_name")
	})
	Poetry getPoetryById(Long poetryId);

	@Select("SELECT detail FROM t_poetry WHERE id=#{poetryId}")
	String getPoetryContentById(Long poetryId);

}
