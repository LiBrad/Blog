package com.brad.blog.dao;

import java.util.List;

import com.brad.blog.bean.Category;

/**
 * @author Brad
 * @version 0.1
 * */
public interface CategoryDao {
	/**
	 * 获取所有分类
	 * */
	public abstract List<Category> getByAllCategoryLabel();
	
	/**
	 * 删除分类
	 * 把is_delete设置为1
	 * */
	public abstract int deleteCategory(int cgId);
	
	/**
	 * 创建一条分类
	 * */
	public abstract int insertCategory(int adminId,String cgName);
	
	/**
	 * 更新分类
	 * */
	public abstract int updateCategory(int cgId,String cgName);
	
	/**
	 * 获取单个分类
	 * */
	public abstract Category getCategory(int id);
	
	/**
	 * 获取分类分页
	 * */
	public abstract List<Category> getCategoryByPage(int curPage,int size);
	
	/**
	 * 计算总数
	 * */
	
	public abstract int getCount();
}
