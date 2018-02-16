package com.brad.blog.dao.impl;

import java.sql.ResultSet;

import com.brad.blog.bean.Admin;
import com.brad.blog.dao.AdminDao;
import com.brad.blog.db.DBPool;
/**
 * @author Brad
 * @version 0.1
 * */
public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(String userName, String password) {
		Admin admin = null;
		
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select * from admin where username=? and password = ?";
			rs = db.doQueryRs(sql, new Object[]{userName,password});
			if(rs.next()){
				admin = new Admin(rs.getInt("id"), rs.getString("username"), null, rs.getString("name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		
		return admin;
	}

}
