package com.brad.blog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 使用数据库连接池，实现增删改查
 * @author Brad
 * @version 0.1
 * */
public class DBPool {
	private String jndiName="java:comp/env/jdbc/blog";
	private Connection conn = null;
	public DBPool() {
		// TODO Auto-generated constructor stub
	}
	public DBPool(String jndiName){
		this.jndiName = jndiName;
	}
	
	public Connection getConnection(){
		try{
			InitialContext context = new InitialContext();//加载jndi配置文件
			DataSource ds = (DataSource) context.lookup(jndiName);//通过jndi获取数据源
			conn = ds.getConnection();//用数据源获取数据库链接
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public ResultSet doQueryRs(String sql,Object[] param) throws SQLException{
		conn = this.getConnection();
	
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				preparedStatement.setObject(i + 1, param[i]);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
		
		return resultSet;
	}
	
	public int doUpdate(String sql,Object[] param) throws SQLException{
		conn = this.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		for (int i = 0; i < param.length; i++) {
			preparedStatement.setObject(i + 1,param[i]);
		}
		int flag = preparedStatement.executeUpdate();
		return flag;
	}
	
	public void close(){
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
