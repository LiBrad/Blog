package com.brad.blog.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brad.blog.bean.Article;
import com.brad.blog.bean.Category;
import com.brad.blog.bean.Comment;
import com.brad.blog.service.ArticleService;
import com.brad.blog.service.CategoryService;
import com.brad.blog.service.CommentService;
import com.brad.blog.service.impl.AritcleServiceImpl;
import com.brad.blog.service.impl.CategoryServiceImpl;
import com.brad.blog.service.impl.CommentServiceImpl;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
@WebServlet("/comment.html")
public class CommentServlet extends HttpServlet {
	private CommentService commentService;
	private ArticleService articleService;
	private CategoryService categoryService;
	
	public CommentServlet() {
		commentService = new CommentServiceImpl();
		articleService = new AritcleServiceImpl();
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
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commId = Integer.parseInt( request.getParameter("commId"));
		
		int flag=commentService.deleteComment(commId);
		
		if(flag > 0){
			request.getSession().setAttribute("msg", "删除成功！");
		}else{
			request.getSession().setAttribute("error", "删除失败！");
		}
		response.sendRedirect("admin.html?action=sysCommentAdmin");
	}
	
	
	
	protected void readMe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int artId = Integer.parseInt(request.getParameter("artId"));
		String curPageStr = request.getParameter("curPage");
		
		Article article = articleService.getByid(artId);
		PageControl pageControl = commentService.getAllByArtId(artId, curPageStr);
		Category category=categoryService.getById(article.getCategoryId());
		articleService.updateCount(artId);
		request.setAttribute("art", article);
		request.setAttribute("category", category);
		
		request.setAttribute("pgList",pageControl.getList());
		request.setAttribute("curPage", pageControl.getCurPage());
		request.setAttribute("totalPages", pageControl.getTotalPages());
		request.setAttribute("total", categoryService.getAllCategory());

		request.getRequestDispatcher("/pages.jsp").forward(request, response);
		
	}
	
	protected void commit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int artId = Integer.parseInt(request.getParameter("artId"));
		String comName = request.getParameter("comm_name");
		String commentContent = request.getParameter("comment_content");
		
		int flag=commentService.insertComment(artId, commentContent, comName);
	
		if(flag > 0){
			request.getSession().setAttribute("msg", "评论成功！");
		}else{
			request.getSession().setAttribute("error", "评论失败！");
		}
		
		response.sendRedirect("comment.html?action=readMe&artId="+artId);
	}
	
}
