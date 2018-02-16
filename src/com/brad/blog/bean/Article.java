package com.brad.blog.bean;

import java.sql.Timestamp;

public class Article {
	private Integer id;
	private String title;
	private Integer userId;
	private Integer categoryId;
	private String content;
	private String summary;
	private Timestamp publishTime;
	private Integer isTop;
	private Integer isDelete;
	private Integer count;
	
	//用于多表查询
	private String name;//获取真实名字
	private String cname;//获取分类名字
	
	
	

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Article(Integer id, String title, Integer userId, Integer categoryId, String content, String summary,
			Timestamp publishTime, Integer isTop, Integer isDelete, Integer count) {
		super();
		this.id = id;
		this.title = title;
		this.userId = userId;
		this.categoryId = categoryId;
		this.content = content;
		this.summary = summary;
		this.publishTime = publishTime;
		this.isTop = isTop;
		this.isDelete = isDelete;
		this.count = count;
	}
	public Article() {
		super();
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", userId=" + userId + ", categoryId=" + categoryId
				+ ", content=" + content + ", summary=" + summary + ", publishTime=" + publishTime + ", isTop=" + isTop
				+ ", isDelete=" + isDelete + ", count=" + count + "]";
	}
}
