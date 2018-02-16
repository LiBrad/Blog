package com.brad.blog.bean;

import java.sql.Timestamp;

public class Comment {
	private Integer id;
	private Integer articalId;
	private String content;
	private Timestamp time;
	private Integer isDelete;
	private String commName;
	
	
	
	public String getCommName() {
		return commName;
	}
	public void setCommName(String commName) {
		this.commName = commName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getArticalId() {
		return articalId;
	}
	public void setArticalId(Integer articalId) {
		this.articalId = articalId;
	}
	public String getContent() {
		return content;
	}
	public void setConntent(String conntent) {
		this.content = conntent;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", articalId=" + articalId + ", content=" + content + ", time=" + time
				+ ", isDelete=" + isDelete + ", commName=" + commName + "]";
	}
	public Comment(Integer id, Integer articalId, String content, Timestamp time, Integer isDelete, String commName) {
		super();
		this.id = id;
		this.articalId = articalId;
		this.content = content;
		this.time = time;
		this.isDelete = isDelete;
		this.commName = commName;
	}
	public Comment() {
		super();
	}
	
	
}
