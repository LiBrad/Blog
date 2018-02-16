package com.brad.blog.bean;

public class BlogInfo {
	private Integer id;
	private Integer userId;
	private String blogName;
	private String description;
	private String annoucement;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAnnoucement() {
		return annoucement;
	}
	public void setAnnoucement(String annoucement) {
		this.annoucement = annoucement;
	}
	public BlogInfo(Integer id, Integer userId, String blogName, String description, String annoucement) {
		super();
		this.id = id;
		this.userId = userId;
		this.blogName = blogName;
		this.description = description;
		this.annoucement = annoucement;
	}
	public BlogInfo() {
		super();
	}
	@Override
	public String toString() {
		return "BlogInfo [id=" + id + ", userId=" + userId + ", blogName=" + blogName + ", description=" + description
				+ ", annoucement=" + annoucement + "]";
	}
	
	
}
