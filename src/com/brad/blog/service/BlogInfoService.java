package com.brad.blog.service;

import com.brad.blog.bean.BlogInfo;

/**
 * @author Brad
 * @version 0.1
 * */
public interface BlogInfoService {

	public abstract BlogInfo getByBlog();
	
	public abstract int updateBlogInfo(int userId,
			String blogName,String description,String annoucement);

	public abstract int insertBlogInfo(int userId,
			String blogName,String description,String annoucement);
}
