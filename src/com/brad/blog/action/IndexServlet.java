package com.brad.blog.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brad.blog.service.ArticleService;
import com.brad.blog.service.CategoryService;
import com.brad.blog.service.impl.AritcleServiceImpl;
import com.brad.blog.service.impl.CategoryServiceImpl;
import com.brad.blog.util.PageControl;

/**
 * @author Brad
 * @version 0.1
 * */
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService articleService;
	private CategoryService categoryService;
	public IndexServlet() {
		articleService = new AritcleServiceImpl();
		categoryService = new CategoryServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String curPageStr = request.getParameter("curPageStr");
		PageControl controlPage = articleService.getData(curPageStr);
		request.setAttribute("pageList", controlPage.getList());
		request.setAttribute("curPageStr", controlPage.getCurPage());
		request.setAttribute("totalPages", controlPage.getTotalPages());
		request.setAttribute("top",articleService.getTop());
		request.setAttribute("total", categoryService.getAllCategory());
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
