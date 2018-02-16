package com.brad.blog.service.impl;

import com.brad.blog.bean.Counter;
import com.brad.blog.dao.CounterDao;
import com.brad.blog.dao.impl.CounterDaoImpl;
import com.brad.blog.service.CounterService;
/**
 * @author Brad
 * @version 0.1
 * */
public class CounterServiceImpl implements CounterService {
	private CounterDao counterDao; 
	public CounterServiceImpl() {
		 counterDao = new CounterDaoImpl();
	 }
	
	@Override
	public Counter getCounter() {
		return counterDao.getCounter();
	}

	@Override
	public int setNum(int num) {
		// TODO Auto-generated method stub
		return counterDao.setNum(num);
	}

}
