package com.brad.blog.dao;

import java.util.List;

import com.brad.blog.bean.Article;
import com.sun.accessibility.internal.resources.accessibility;
/**
 * @author Brad
 * @version 0.1
 * */
public interface ArticleDao {
	/**
	 * 根据Id获取文章
	 * */
	public abstract Article getById(int id);
	
	/**
	 * 新增一篇文章
	 * */
	public abstract int inserArticle(String title,int userId, int cgId,
			String content,String summary);
	
	/**
	 * 更新文章
	 * 
	 * */
	public abstract int updateArticle(int arId,String title,int userId, int cgId,
			String content,String summary);
	
	/**
	 * 删除文章 把is_delete字段设置为1
	 * */
	public abstract int deleteArtical(int arId,int adminId);
	
	/**
	 * 获取当期所有未被逻辑删除的文章
	 * */
	public abstract List<Article> getAllArtical();
	
	/**
	 * 搜索文章
	 * */
	
	public abstract int searchCount(String q);
	
	public abstract List<Article> search(String q,int curPage,int size);//根据关键字查询
	public abstract List<Article> getArticleByPage(int curPage,int size);//获取主页文章分页
	public abstract List<Article> getArticleByAdmin(int admin_id,int curPage,int size);//获取主页文章分页
	public abstract List<Article> getArticleByLabelPage(int cgId,int curPage,int size);//获取栏目里面的文章
	/**
	 * 查询点击率前10的文章
	 * */
	public abstract List<Article> topTenArticle();
	
	public abstract List<Article> getAdminId(int user_id);
	
	/**
	 * 文章点击率+1
	 * */
	public int updateCount(int artId);
}
