package com.brad.blog.service.impl;

import java.util.List;

import com.brad.blog.bean.Category;
import com.brad.blog.dao.CategoryDao;
import com.brad.blog.dao.impl.CategoryDaoImpl;
import com.brad.blog.service.CategoryService;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	
	public CategoryServiceImpl() {
		categoryDao = new CategoryDaoImpl();
	}
	
	@Override
	public Category getById(int id) {

		return categoryDao.getCategory(id);
	}

	@Override
	public int intsertCategory(int adminId, String cgName) {
		// TODO Auto-generated method stub
		return categoryDao.insertCategory(adminId, cgName);
	}

	@Override
	public int updateCategory(int cgId, String cgName) {
		// TODO Auto-generated method stub
		return categoryDao.updateCategory(cgId, cgName);
	}

	@Override
	public int deleteCategory(int cgId) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategory(cgId);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getByAllCategoryLabel();
	}

	@Override
	public PageControl getCategoryByPage(String curPageStr) {
		PageControl pc = new PageControl(curPageStr, categoryDao.getCount());
		List<Category> catList = categoryDao.getCategoryByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(catList);
		return pc;
	}

}
