package com.brad.blog.dao;

import com.brad.blog.bean.Counter;

/**
 * @author Brad
 * @version 0.1
 * */
public interface CounterDao {

	public abstract Counter getCounter();

	public abstract int setNum(int num);
}
