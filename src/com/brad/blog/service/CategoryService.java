package com.brad.blog.service;

import java.util.List;

import com.brad.blog.bean.Category;
import com.brad.blog.util.PageControl;

/**
 * @author Brad
 * @version 0.1
 * */
public interface CategoryService {

	public abstract Category getById(int id);
	
	public abstract int intsertCategory(int adminId,String cgName);
	
	public abstract int updateCategory(int cgId,String cgName);
	
	public abstract int deleteCategory(int cgId);
	
	public abstract List<Category> getAllCategory();
	
	public abstract PageControl getCategoryByPage(String curPageStr);
	
}
