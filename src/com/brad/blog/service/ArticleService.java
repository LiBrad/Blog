package com.brad.blog.service;

import java.util.List;

import com.brad.blog.bean.Article;
import com.brad.blog.util.PageControl;

/**
 * @author Brad
 * @version 0.1
 * */
public interface ArticleService {
	/**
	 * 添加文章
	 * */
	public abstract int insertArtical(String title,int adminId,
			int cgId,String content,String summary);
	
	/**
	 * 用于文章的分页
	 * */
	public abstract PageControl getData(String curPageStr);
	
	
	/**
	 * 模糊查询(如果查询内容过多，可能发生问题)
	 * */
	public abstract PageControl search(String q,String curPageStr);
	
	/**
	 * 删除文章
	 * */
	public abstract int deleteArtical(int artId,int adminId);
	
	/**
	 * 更新文章
	 * */
	public abstract int updateArtical(int artId,String title,int userId,
			int cgId,String content,String summary);
	/**
	 * 获取分类文章
	 * */
	public abstract PageControl getByLabelCgId(int cgid,String curPageStr);

	/**
	 * 获取置顶文章
	 * */
	public abstract List<Article> topTenArticle();
	
	/**
	 * 获取单个文章
	 * */
	public abstract Article getByid(int id);
	
	/**
	 * 设置文章访问次数
	 * */
	public abstract int updateCount(int artId);
	
	/**
	 * 获取全部文章
	 * */
	public  abstract List<Article> getAllArticle();
	
	
	public abstract PageControl getByPageUserId(String curPageStr, int userId);
	
	public abstract List<Article> getTop();

}
