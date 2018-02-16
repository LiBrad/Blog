package com.brad.blog.service.impl;

import com.brad.blog.bean.BlogInfo;
import com.brad.blog.dao.BlogInfoDao;
import com.brad.blog.dao.impl.BlogInfoDaoImpl;
import com.brad.blog.service.BlogInfoService;
/**
 * @author Brad
 * @version 0.1
 * */
public class BlogInfoServiceImpl implements BlogInfoService{
	private BlogInfoDao blogInfoDao;
	public BlogInfoServiceImpl() {
		blogInfoDao = new BlogInfoDaoImpl();
	}
	
	@Override
	public BlogInfo getByBlog() {
		return blogInfoDao.getByBlogInfo();
	}

	@Override
	public int updateBlogInfo(int userId, String blogName, String description, String annoucement) {
		// TODO Auto-generated method stub
		return blogInfoDao.updateBlogInfo(userId, blogName, description, annoucement);
	}

	@Override
	public int insertBlogInfo(int userId, String blogName, String description, String annoucement) {
		// TODO Auto-generated method stub
		return blogInfoDao.intsertBlogInfo(userId, blogName, description, annoucement);
	}

}
