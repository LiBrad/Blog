package com.brad.blog.dao;

import java.util.List;

import com.brad.blog.bean.Comment;

/**
 *@author Brad
 *@version 0.1
 * */
public interface CommentDao {
	/**
	 * 插入一套评论记录
	 * */
	
	public abstract int insertComment(int artId,String conent,String commName);
	

	/**
	 * 删除一条评论
	 * 
	 * 将is_delete变为1
	 * */
	public abstract int deleteComment(int cmtId);
	
	/**
	 * 评论分页
	 * */
	
	public abstract List<Comment> getCommentByPage(int artId,int curPage,int size);
	
	/**
	 * 获取全部评论
	 * */
	public abstract List<Comment> getAllComment();
	
	public abstract int getCountAltComment(int artId);
	
	/**
	 * 获取单条评论
	 * */
	public abstract Comment getComment(int cmtId);
	public abstract List<Comment> getCommentPages(int curPage, int size);
}
