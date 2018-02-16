package com.brad.blog.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.brad.blog.bean.Comment;
import com.brad.blog.dao.CommentDao;
import com.brad.blog.db.DBPool;
/**
 * @author Brad
 * @version 0.1
 * */
public class CommentDaoImpl implements CommentDao{

	@Override
	public int insertComment(int artId, String conent,String commName) {
		DBPool dbPool = new DBPool();
		int res = -1;
		try {
			String sql = "insert into comment(artical_id,content,comm_name) values(?,?,?)";
			res = dbPool.doUpdate(sql, new Object[]{artId,conent,commName});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return res;
	}

	

	@Override
	public int deleteComment(int cmtId) {
		DBPool dbPool = new DBPool();
		int res = -1;
		try {
			String sql = "update comment set is_delete=1 where id = ?";
			res = dbPool.doUpdate(sql, new Object[]{cmtId});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return res;
	}



	@Override
	public List<Comment> getCommentByPage(int artId, int curPage, int size) {
		ResultSet rs = null;
		List<Comment> romList = null;
		DBPool dbPool = new DBPool();
		int start = (curPage - 1) * size;
		try {
			romList = new ArrayList<Comment>();
			String sql = "select * from comment where artical_id = ? and is_delete = 0 order by time desc limit ?,?";
			rs = dbPool.doQueryRs(sql, new Object[]{artId,start,size});
			while(rs.next()){
				Comment comment = new Comment(rs.getInt("id"), rs.getInt("artical_id"), rs.getString("content"), rs.getTimestamp("time"), rs.getInt("is_delete"),rs.getString("comm_name"));
				romList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return romList;
	}
	public List<Comment> getCommentPages(int curPage, int size) {
		ResultSet rs = null;
		List<Comment> romList = null;
		DBPool dbPool = new DBPool();
		int start = (curPage - 1) * size;
		try {
			romList = new ArrayList<Comment>();
			String sql = "select * from comment where is_delete = 0 order by time desc limit ?,?";
			rs = dbPool.doQueryRs(sql, new Object[]{start,size});
			while(rs.next()){
				Comment comment = new Comment(rs.getInt("id"), rs.getInt("artical_id"), rs.getString("content"), rs.getTimestamp("time"), rs.getInt("is_delete"),rs.getString("comm_name"));
				romList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return romList;
	}


	@Override
	public List<Comment> getAllComment() {
		ResultSet rs = null;
		List<Comment> romList = null;
		DBPool dbPool = new DBPool();
		try {
			romList = new ArrayList<Comment>();
			String sql = "select * from comment where is_delete = 0";
			rs = dbPool.doQueryRs(sql, new Object[]{});
			while(rs.next()){
				Comment comment = new Comment(rs.getInt("id"), rs.getInt("artical_id"), rs.getString("content"), rs.getTimestamp("time"), rs.getInt("is_delete"),rs.getString("comm_name"));
				romList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return romList;
	}



	@Override
	public Comment getComment(int cmtId) {
		Comment comment = null;
		ResultSet rs = null;
		DBPool dbPool = new DBPool();
		try {
			String sql = "select * from comment where id=? and id_delete = 0";
			rs = dbPool.doQueryRs(sql, new Object[]{cmtId});
			while(rs.next()){
				comment = new Comment(rs.getInt("id"), rs.getInt("artical_id"), rs.getString("content"), rs.getTimestamp("time"), rs.getInt("is_delete"),rs.getString("comm_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return comment;
	}



	@Override
	public int getCountAltComment(int artId) {
		int count = -1;
		ResultSet rs = null;
		DBPool dbPool = new DBPool();
		try {
			String sql = "select count(id) from comment where artical_id=? and is_delete = 0";
			rs = dbPool.doQueryRs(sql, new Object[]{artId});
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		return count;
	}

	

}
