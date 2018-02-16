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
import com.brad.blog.service.ArticleService;
import com.brad.blog.service.CategoryService;
import com.brad.blog.service.impl.AritcleServiceImpl;
import com.brad.blog.service.impl.CategoryServiceImpl;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
@WebServlet("/article.html")
public class ArticleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ArticleService articleService;
	private CategoryService categoryService;
	
	public ArticleServlet() {
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
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> cgList = categoryService.getAllCategory();
		request.setAttribute("cgList", cgList);
		request.getRequestDispatcher("admin/addArticle.jsp").forward(request, response);
	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		String title = request.getParameter("title");
		int cgId = Integer.parseInt(request.getParameter("category"));
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		int flag = articleService.insertArtical(title, adminId, cgId, content, summary);
		
		if(flag > 0){
			request.getSession().setAttribute("msg", "文章添加成功！");
		}else{
			request.getSession().setAttribute("error", "文章添加失败！");
		}
		
		response.sendRedirect("admin.html?action=sysArticalAdmin&adminId="+adminId);
	}
	
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		int articleId = Integer.parseInt(request.getParameter("artId"));
		
		int flag = articleService.deleteArtical(articleId,adminId);
		
		if(flag > 0){
			request.getSession().setAttribute("msg", "删除成功！");
		}else{
			request.getSession().setAttribute("error", "删除失败！");
		}
		
		response.sendRedirect("admin.html?action=sysArticalAdmin&adminId="+adminId);
	}
	
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int	artId = Integer.parseInt(request.getParameter("artId"));
		List<Category> catList = categoryService.getAllCategory();
		Article article = articleService.getByid(artId);
		
		request.setAttribute("cgList", catList);
		request.setAttribute("art", article);
		request.getRequestDispatcher("admin/updateArtical.jsp").forward(request, response);
	}
	
	
	protected void saveupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int	artId = Integer.parseInt(request.getParameter("artId"));
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		String title = request.getParameter("title");
		int cgId = Integer.parseInt(request.getParameter("category"));
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		
		int flag = articleService.updateArtical(artId, title, adminId, cgId, content, summary);
		if(flag > 0){
			request.getSession().setAttribute("msg", "修改成功！");
		}else{
			request.getSession().setAttribute("error", "修改失败！");
		}
		response.sendRedirect("admin.html?action=sysArticalAdmin&adminId="+adminId);
	}
	
	
	protected void catePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String curPageStr = request.getParameter("curPageStr");
		PageControl pageControl=articleService.getByLabelCgId(cateId, curPageStr);
		
		
		request.setAttribute("cateList", pageControl.getList());
		request.setAttribute("curPageStr", pageControl.getCurPage());
		request.setAttribute("totalPages", pageControl.getTotalPages());
		request.setAttribute("top",articleService.getTop());
		request.setAttribute("total", categoryService.getAllCategory());
		request.getRequestDispatcher("/cateIndex.jsp").forward(request, response);
		
	}
	
	
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = request.getParameter("q");
		String curPageStr = request.getParameter("curPageStr");
		
		PageControl control = articleService.search(q, curPageStr);
		
		request.setAttribute("searchList", control.getList());
		request.setAttribute("curPageStr", control.getCurPage());
		request.setAttribute("totalPages", control.getTotalPages());
		
		request.setAttribute("top",articleService.getTop());
		request.setAttribute("total", categoryService.getAllCategory());
		request.getRequestDispatcher("/search.jsp").forward(request, response);

	}
}
