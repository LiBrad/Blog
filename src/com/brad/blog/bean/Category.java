package com.brad.blog.bean;

public class Category {
	private Integer id;
	private Integer userId;
	private String categoryName;
	private Integer isDelete;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Category(Integer id, Integer userId, String categoryName, Integer isDelete) {
		super();
		this.id = id;
		this.userId = userId;
		this.categoryName = categoryName;
		this.isDelete = isDelete;
	}
	public Category() {
		super();
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", userId=" + userId + ", categoryName=" + categoryName + ", isDelete=" + isDelete
				+ "]";
	}
	
}
