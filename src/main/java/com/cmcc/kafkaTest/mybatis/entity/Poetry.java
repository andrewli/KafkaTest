package com.cmcc.kafkaTest.mybatis.entity;

public class Poetry {
	private long id;
	private long poetryId;
	private String poetryName;
	private String authorName;
	private long authorId;
	private String time;
	private String type;
	private String content;
	private String translation;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPoetryId() {
		return poetryId;
	}
	public void setPoetryId(long poetryId) {
		this.poetryId = poetryId;
	}
	public String getPoetryName() {
		return poetryName;
	}
	public void setPoetryName(String poetryName) {
		this.poetryName = poetryName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
	
	
}
