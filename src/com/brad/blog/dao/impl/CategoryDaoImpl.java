package com.brad.blog.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.brad.blog.bean.Category;
import com.brad.blog.dao.CategoryDao;
import com.brad.blog.db.DBPool;
/**
 * @author Brad
 * @version 0.1
 * */
public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getByAllCategoryLabel() {
		List<Category> cateList = null;
		DBPool dbPool = new DBPool();
		ResultSet rs = null;
		try {
			String sql = "select * from category where is_delete=0";
			rs = dbPool.doQueryRs(sql,new Object[]{});
			cateList = new ArrayList<Category>();
			while(rs.next()){
				Category category = new Category(rs.getInt("id"), rs.getInt("user_id"),rs.getString("category_name"), rs.getInt("is_delete"));
				cateList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		
		return cateList;
	}

	@Override
	public int deleteCategory(int cgId) {
		DBPool dbPool = new DBPool();
		int rsn = -1;
		try {
			String sql = "update category set id_delete = 1 where id = ?";
			rsn=dbPool.doUpdate(sql, new Object[]{cgId});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();

		}
		
		
		return rsn;
	}

	@Override
	public int insertCategory(int adminId, String cgName) {
		DBPool dbPool = new DBPool();
		int rsn = -1;
		try {
			String sql = "insert into category(user_id,category_name) values(?,?)";
			rsn=dbPool.doUpdate(sql, new Object[]{adminId,cgName});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();

		}
		
		
		return rsn;
	}

	@Override
	public int updateCategory(int cgId, String cgName) {
		DBPool dbPool = new DBPool();
		int rsn = -1;
		try {
			String sql = "update category set category_name = ? where id = ?";
			rsn=dbPool.doUpdate(sql, new Object[]{cgName,cgId});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();

		}
		
		
		return rsn;
	}

	@Override
	public List<Category> getCategoryByPage(int curPage, int size) {
		List<Category> cateList = null;
		DBPool dbPool = new DBPool();
		int start = (curPage - 1) * size;
		ResultSet rs = null;
		try {
			cateList = new ArrayList<Category>();
			String sql = "select * from category where is_delete=0 order by id DESC limit ?,?";
			rs = dbPool.doQueryRs(sql,new Object[]{start,size});
			while(rs.next()){
				Category category = new Category(rs.getInt("id"), rs.getInt("user_id"),rs.getString("category_name"), rs.getInt("is_delete"));
				cateList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		
		return cateList;
	}

	@Override
	public Category getCategory(int id) {
		Category category = null;
		DBPool dbPool = new DBPool();
		ResultSet rs = null;
		try {
			String sql = "select * from category where is_delete=0 and id=?";
			rs = dbPool.doQueryRs(sql,new Object[]{id});
			while(rs.next()){
				 category = new Category(rs.getInt("id"), rs.getInt("user_id"),rs.getString("category_name"), rs.getInt("is_delete"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		
		
		return category;
	}

	@Override
	public int getCount() {
		DBPool dbPool = new DBPool();
		ResultSet rs = null;
		int count = -1;
		
		try {
			String sql = "select count(*) from category where is_delete = 0";
			rs=dbPool.doQueryRs(sql, new Object[]{});
			while(rs.next()){
				count  = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();

		}
		return count;
	}

}
