package com.brad.blog.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brad.blog.bean.Admin;
import com.brad.blog.bean.Article;
import com.brad.blog.bean.Category;
import com.brad.blog.bean.Comment;
import com.brad.blog.bean.Counter;
import com.brad.blog.service.AdminService;
import com.brad.blog.service.ArticleService;
import com.brad.blog.service.CategoryService;
import com.brad.blog.service.CommentService;
import com.brad.blog.service.CounterService;
import com.brad.blog.service.impl.AdminServiceImpl;
import com.brad.blog.service.impl.AritcleServiceImpl;
import com.brad.blog.service.impl.CategoryServiceImpl;
import com.brad.blog.service.impl.CommentServiceImpl;
import com.brad.blog.service.impl.CounterServiceImpl;
import com.brad.blog.util.PageControl;
/**
 * @author Brad
 * @version 0.1
 * */
@WebServlet("/admin.html")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private ArticleService articleService;
	private CounterService counterService;
	private CommentService commentService;
	private CategoryService categoryService;
	
	public AdminServlet() {
		adminService = new AdminServiceImpl();
		articleService = new AritcleServiceImpl();
		counterService = new CounterServiceImpl();
		commentService = new CommentServiceImpl();
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
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		
		Admin admin = adminService.login(userName, passWord);
			
		if (null != admin) {
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect("admin.html?action=index");
		}else{
			request.getSession().setAttribute("msg", "用户名或密码错误");
			response.sendRedirect("adminLogin.jsp");
		}
	}
	protected void  index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> artList = articleService.getAllArticle();	//获取所有文章
		List<Comment> comList = commentService.getAllComment();//获取评论总数
		List<Category> cagList =categoryService.getAllCategory();//获取总分类
		Counter cnt = counterService.getCounter();	//获取网站总访问人数
		request.getSession().setAttribute("artList", artList);
		request.getSession().setAttribute("comList", comList);
		request.getSession().setAttribute("cagList", cagList);
		request.getSession().setAttribute("cnt", cnt);
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	}
	
	protected void  logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		if(null != httpSession){
			httpSession.removeAttribute("admin");
			httpSession.removeAttribute("artList");
			httpSession.removeAttribute("comList");
			httpSession.removeAttribute("cnt");
			httpSession.removeAttribute("cagList");

		}
		
		response.sendRedirect("index.html");
	}
	
	protected void sysArticalAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adminIdStr=request.getParameter("adminId");
		int admin = Integer.parseInt(adminIdStr);
		
		//添加用于分页
		String curPageStr = request.getParameter("curPage");
		PageControl pc = articleService.getByPageUserId(curPageStr, admin);
		request.setAttribute("curPage", pc.getCurPage());
		request.setAttribute("totalPages", pc.getTotalPages());
		
		request.setAttribute("artList", pc.getList());
		
		request.getRequestDispatcher("admin/articleManage.jsp").forward(request, response);
	}
	
	protected void  sysCategoryAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curPageStr = request.getParameter("curPage");
		PageControl pc = categoryService.getCategoryByPage(curPageStr);
		request.setAttribute("cateList", pc.getList());
		request.setAttribute("curPage", pc.getCurPage());
		request.setAttribute("totalPages", pc.getTotalPages());
		
		request.getRequestDispatcher("admin/categoryManage.jsp").forward(request, response);
	}
	
	protected void  sysCommentAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curPageStr = request.getParameter("curPageStr");	
		PageControl control = commentService.getAllCommList(curPageStr);
		request.setAttribute("commList", control.getList());
		request.setAttribute("total", control.getTotalPages());
		request.setAttribute("curPageStr", control.getCurPage());
		request.getRequestDispatcher("admin/commentManage.jsp").forward(request, response);

	}
}
