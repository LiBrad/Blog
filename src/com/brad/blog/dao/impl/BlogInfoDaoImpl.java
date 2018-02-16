package com.brad.blog.dao.impl;

import java.sql.ResultSet;

import com.brad.blog.bean.BlogInfo;
import com.brad.blog.dao.BlogInfoDao;
import com.brad.blog.db.DBPool;
 /**
  * @author Brad
  * @version 0.1
  * */
public class BlogInfoDaoImpl implements BlogInfoDao {

	@Override
	public int intsertBlogInfo(int adminId, String blogName, String description, String annoucement) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "insert into blog_info(user_id,blog_name,description,annoucement) values(?,?,?,?)";
			res = db.doUpdate(sql, new Object[]{adminId,blogName,description,annoucement});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		
		return res;
	}

	@Override
	public BlogInfo getByBlogInfo() {
		BlogInfo blogInfo = null;
		DBPool db = new DBPool();
		ResultSet rs = null;
		try {
			String sql = "select * from blog_info";
			rs = db.doQueryRs(sql, new Object[]{});
			while(rs.next()){
				blogInfo  = new BlogInfo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("blog_name"), rs.getString("description"), rs.getString("annoucement"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return blogInfo;
	}

	@Override
	public int updateBlogInfo(int userId, String blogName, String description, String annoucement) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "update blog_info set blogName=?,description=?,annoucement=? where user_id = ?";
			res = db.doUpdate(sql, new Object[]{blogName,description,annoucement,userId});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		
		return res;
	}

}
