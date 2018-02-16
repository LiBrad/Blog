package com.brad.blog.dao;

import com.brad.blog.bean.Admin;

/**
 * @author Brad
 * @version 0.1
 * */

public interface AdminDao {
	public abstract Admin login(String userName,String password);
	
}
