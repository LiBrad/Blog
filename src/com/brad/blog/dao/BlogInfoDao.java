package com.brad.blog.dao;

import com.brad.blog.bean.BlogInfo;

/**
 * @author Brad
 * @version 0.1 
 * */
public interface BlogInfoDao {
	/**
	 *  增加博客信息
	 * */
	public abstract int intsertBlogInfo(int adminId,String blogName,
			String description,String annoucement);
	
	/**
	 * 查询博客信息
	 * */
	public abstract BlogInfo getByBlogInfo();
	
	/**
	 * 更新博客信息
	 * */
	public abstract int updateBlogInfo(int userId,String blogName,
			String description,String annoucement);
}
