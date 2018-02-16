package com.brad.blog.service.impl;

import java.util.List;

import com.brad.blog.bean.Admin;
import com.brad.blog.bean.Article;
import com.brad.blog.bean.Category;
import com.brad.blog.dao.AdminDao;
import com.brad.blog.dao.ArticleDao;
import com.brad.blog.dao.CategoryDao;
import com.brad.blog.dao.impl.AdminDaoImpl;
import com.brad.blog.dao.impl.ArticleDaoImpl;
import com.brad.blog.dao.impl.CategoryDaoImpl;
import com.brad.blog.service.AdminService;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
public class AdminServiceImpl implements AdminService{
	private AdminDao adminDao;
	private CategoryDao categoryDao;
	private ArticleDao articleDao;
	
	public AdminServiceImpl() {
		adminDao = new AdminDaoImpl();
		categoryDao = new CategoryDaoImpl();
		articleDao = new ArticleDaoImpl();
	}
	
	@Override
	public Admin login(String userName, String password) {
		return adminDao.login(userName, password);
	}

	@Override
	public PageControl getPageOfAritcle(String curPageStr) {
		PageControl pageControl = new PageControl(curPageStr, articleDao.getAllArtical().size());
		List<Article> aList = articleDao.getArticleByPage(pageControl.getCurPage(), pageControl.getPageSize());
		pageControl.setList(aList);
		return pageControl;
	}

	@Override
	public PageControl getCategoryLabel(String curPageStr) {
		PageControl pageControl = new PageControl(curPageStr, categoryDao.getByAllCategoryLabel().size());
		List<Category> aList =categoryDao.getCategoryByPage(pageControl.getCurPage(), pageControl.getPageSize());
		pageControl.setList(aList);
		return pageControl;
	}
	
}
