package com.brad.blog.bean;

public class Admin{
	private Integer id;
	private String username;
	private String password;
	private String name;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(Integer id, String username, String password, String name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + "]";
	}
	
	
}
