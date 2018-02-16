package com.brad.blog.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.brad.blog.bean.Counter;
import com.brad.blog.service.CounterService;
import com.brad.blog.service.impl.CounterServiceImpl;


@WebListener
public class CountOnlineListener implements HttpSessionListener {
	 private CounterService cntService=new CounterServiceImpl();
	
	 public void sessionCreated(HttpSessionEvent hse)  
     {  
		 Counter cnt = cntService.getCounter();
		 int num = cnt.getNum();
		 HttpSession session = hse.getSession();
		 if (session.isNew()) {
			 num = num + 1;
			 cntService.setNum(num);
		 }		 
		 session.setAttribute("num", num);
     }  
	 
     public void sessionDestroyed(HttpSessionEvent hse)  
     {  
    	
     }  
}
