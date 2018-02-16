package com.brad.blog.bean;

public class Counter {
	private Integer id;
	private Integer num;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Counter(Integer id, Integer num) {
		super();
		this.id = id;
		this.num = num;
	}
	public Counter() {
		super();
	}
	@Override
	public String toString() {
		return "Counter [id=" + id + ", num=" + num + "]";
	}
	
}
