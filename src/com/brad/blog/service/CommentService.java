package com.brad.blog.service;

import java.util.List;

import com.brad.blog.bean.Comment;
import com.brad.blog.util.PageControl;

/**
 * @author Brad
 * @version 0.1
 * */
public interface CommentService {
	 public abstract int insertComment(int artId,String content,String commName);
	 
	 public abstract int deleteComment(int cmtId);
	 
	 public abstract PageControl getAllByArtId(int artId,String curPageStr);

	 public abstract PageControl getAllCommList(String curPageStr);

	 public abstract List<Comment> getAllComment();
}
