package com.brad.blog.service;

import com.brad.blog.bean.Counter;

/**
 * @author Brad
 * @version 0.1
 * */
public interface CounterService {
	/**
	 * 获取网站访问计数
	 * */
	public abstract Counter getCounter();
	
	/**
	 * 设置网站访问数
	 * */
	public abstract int setNum(int num);
}
