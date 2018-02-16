package com.brad.blog.service.impl;

import java.util.List;

import com.brad.blog.bean.Article;
import com.brad.blog.dao.ArticleDao;
import com.brad.blog.dao.impl.ArticleDaoImpl;
import com.brad.blog.service.ArticleService;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
public class AritcleServiceImpl implements ArticleService{
	private ArticleDao artDao;
	
	public AritcleServiceImpl() {
		artDao = new ArticleDaoImpl();
	}
	
	@Override
	public int insertArtical(String title, int adminId, int cgId, String content, String summary) {
		return artDao.inserArticle(title, adminId, cgId, content, summary);
	}

	@Override
	public PageControl getData(String curPageStr) {
		int count = artDao.getAllArtical().size();
		PageControl pc = new PageControl(curPageStr, count);
		List<Article> arList = artDao.getArticleByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(arList);
		return pc;
	}

	@Override
	public PageControl search(String q,String curPageStr) {
		PageControl control = new PageControl(curPageStr, artDao.searchCount(q));
		List<Article> articles = artDao.search(q, control.getCurPage(), control.getPageSize());
		control.setList(articles);
		return control;
	}

	@Override
	public int deleteArtical(int artId,int adminId) {
		// TODO Auto-generated method stub
		return artDao.deleteArtical(artId, adminId);
	}

	@Override
	public int updateArtical(int artId, String title, int userId, int cgId, String content, String summary) {
		// TODO Auto-generated method stub
		return artDao.updateArticle(artId, title, userId, cgId, content, summary);
	}

	@Override
	public PageControl getByLabelCgId(int cgid,String curPageStr) {
		int count = artDao.getAllArtical().size();
		PageControl pc = new PageControl(curPageStr, count);
		List<Article> arList = artDao.getArticleByLabelPage(cgid,pc.getCurPage(), pc.getPageSize());
		pc.setList(arList);
		return pc;
	}

	@Override
	public List<Article> topTenArticle() {
		return artDao.topTenArticle();
	}

	@Override
	public Article getByid(int id) {
		// TODO Auto-generated method stub
		return artDao.getById(id);
	}

	@Override
	public int updateCount(int artId) {
		// TODO Auto-generated method stub
		return artDao.updateCount(artId);
	}

	@Override
	public List<Article> getAllArticle() {
		// TODO Auto-generated method stub
		return artDao.getAllArtical();
	}

	@Override
	public PageControl getByPageUserId(String curPageStr, int adminId) {
		PageControl pc = new PageControl(curPageStr, artDao.getAdminId(adminId).size());
		List<Article> arList = artDao.getArticleByAdmin(adminId, pc.getCurPage(),pc.getPageSize());
		pc.setList(arList);
		return pc;
	}



	@Override
	public List<Article> getTop() {
		List<Article> arList = artDao.topTenArticle();
		return arList;
	}

}
