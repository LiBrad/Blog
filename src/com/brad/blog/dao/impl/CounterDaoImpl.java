package com.brad.blog.dao.impl;

import java.sql.ResultSet;

import com.brad.blog.bean.Counter;
import com.brad.blog.dao.CounterDao;
import com.brad.blog.db.DBPool;
/**
 * @author Brad
 * @version 0.1
 * */
public class CounterDaoImpl implements CounterDao {

	@Override
	public Counter getCounter() {
		DBPool dbPool = new DBPool();
		ResultSet resultSet = null;
		Counter counter = null;
		try {
			String sql = "select * from counter where id = 1";
			resultSet = dbPool.doQueryRs(sql, new Object[]{});
			while(resultSet.next()){
				counter = new Counter(resultSet.getInt("id"), resultSet.getInt("num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		return counter;
	}

	@Override
	public int setNum(int num) {
		DBPool dbPool = new DBPool();
		int res = -1;
		try {
			String sql = "update counter set num= ? where id = 1";
			res = dbPool.doUpdate(sql, new Object[]{num});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbPool.close();
		}
		return res;
	}

}
