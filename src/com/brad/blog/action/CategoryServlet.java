package com.brad.blog.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brad.blog.bean.Category;
import com.brad.blog.service.CategoryService;
import com.brad.blog.service.impl.CategoryServiceImpl;
/**
 * @author Brad
 * @version 0.1
 * */
@WebServlet("/catergory.html")
public class CategoryServlet extends HttpServlet{
	private CategoryService categoryService;
	
	public CategoryServlet() {
		categoryService = new CategoryServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	//通过反射操作
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		String actionStr = request.getParameter("action");
		Class clazz = this.getClass();
		try {
			Method method=clazz.getDeclaredMethod(actionStr, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateName = request.getParameter("cateName");
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		
		int flag =categoryService.intsertCategory(adminId, cateName);
		
		if(flag > 0){
			request.getSession().setAttribute("msg", "添加成功！");
		}else{
			request.getSession().setAttribute("error", "添加失败！");
		}
		response.sendRedirect("admin.html?action=sysCategoryAdmin");
	}
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		Category category=categoryService.getById(cateId);
		request.setAttribute("cate", category);
		request.getRequestDispatcher("admin/updateCategory.jsp").forward(request, response);
	}
	
	protected void saveUpate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("cgId"));
		String cateName = request.getParameter("cateName");
		int flag = categoryService.updateCategory(cateId, cateName);
		
		if (flag > 0) {
			request.getSession().setAttribute("msg", "修改成功！");
		}else{
			request.getSession().setAttribute("error", "修改失败！");
		}
		
		response.sendRedirect("admin.html?action=sysCategoryAdmin");
	}
}
