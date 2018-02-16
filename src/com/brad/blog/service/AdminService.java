package com.brad.blog.service;

import com.brad.blog.bean.Admin;
import com.brad.blog.util.PageControl;

/**
 * @author Brad
 * @version 0.1
 * */
public interface AdminService {
	public abstract Admin login(String userName,String password);
	
	/**
	 * 管理文章分页
	 * */
	public abstract PageControl getPageOfAritcle(String curPageStr);

	/**
	 * 管理系统栏目分页
	 * */
	public abstract PageControl getCategoryLabel(String curPageStr)
	;
}
