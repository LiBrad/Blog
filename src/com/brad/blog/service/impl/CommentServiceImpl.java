package com.brad.blog.service.impl;

import java.util.List;

import com.brad.blog.bean.Comment;
import com.brad.blog.dao.CommentDao;
import com.brad.blog.dao.impl.CommentDaoImpl;
import com.brad.blog.service.CommentService;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
public class CommentServiceImpl implements CommentService{
	private CommentDao	commentDao;
	
	public CommentServiceImpl() {
		commentDao = new CommentDaoImpl();
	}
	
	@Override
	public int insertComment(int artId, String content,String commName) {
		if(commName == ""){
			commName="匿名用户";
		}
		return commentDao.insertComment(artId, content,commName);
	}

	@Override
	public int deleteComment(int cmtId) {
		return commentDao.deleteComment(cmtId);
	}

	@Override
	public PageControl getAllByArtId(int artId, String curPageStr) {
		int total=commentDao.getCountAltComment(artId);
		PageControl pageControl = new PageControl(curPageStr, total);
		List<Comment> cmtList=commentDao.getCommentByPage(artId, pageControl.getCurPage(), pageControl.getPageSize());
		pageControl.setList(cmtList);
		
		return pageControl;
	}

	@Override
	public List<Comment> getAllComment() {
		return commentDao.getAllComment();
	}

	@Override
	public PageControl getAllCommList(String curPageStr) {
		PageControl pageControl = new PageControl(curPageStr, commentDao.getAllComment().size());
		List<Comment> commList = commentDao.getCommentPages(pageControl.getCurPage(), pageControl.getPageSize());
		pageControl.setList(commList);
		return pageControl;
	}

}
