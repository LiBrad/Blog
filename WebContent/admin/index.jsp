<%@ page language="java" import="java.util.*"
	import="com.brad.blog.bean.*" import="com.brad.blog.impl.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="frame/Header.jsp"></jsp:include>
<c:if test="${sessionScope.admin != null}">
	<div class="container">
		<div class="col-lg-12">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

				<div class="navbar-header">
					<a class="navbar-brand" href="<%=basePath%>admin/index.jsp">Brad
						Blog 后台管理</a>
				</div>

				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav side-nav">
						<li><a class="btn active"
							href="<%=basePath%>admin/index.jsp"><i
								class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
						<li><a class="btn active"
							href="<%=basePath%>admin.html?action=sysArticalAdmin&adminId=${sessionScope.admin.getId()}"><i
								class="glyphicon glyphicon-cog"></i> 文章管理</a></li>
						<li><a class="btn active"
							href="<%=basePath%>admin.html?action=sysCategoryAdmin&adminId=${sessionScope.admin.getId()}"><i
								class="glyphicon glyphicon-edit "></i> 导航管理</a></li>
						<li><a class="btn active"
							href="<%=basePath%>admin.html?action=sysCommentAdmin"><i
								class="glyphicon glyphicon-edit "></i> 评论管理</a></li>
					</ul>


					<ul class="nav navbar-nav navbar-right navbar-user">
						<li class="dropdown user-dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"><i
								class="glyphicon glyphicon-user"></i>${sessionScope.admin.getName()}
								<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<%=basePath%>admin.html?action=logout"><i
										class="glyphicon glyphicon-off"></i> 登出</a></li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</div>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1>Brad Blog System</h1>
					<ol class="breadcrumb">
						<li class="active"><i class="glyphicon glyphicon-dashboard"></i>
							控制面板</li>
					</ol>

					<div class="row">
						<div class="col-lg-3">
							<div class="panel panel-info">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-6">
											<i class="glyphicon glyphicon-search"></i>
										</div>
										<div class="col-xs-6 text-right">
											<p class="announcement-heading">${sessionScope.cnt.getNum()}</p>
											<p class="announcement-text">网站来访人数</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-3">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-6">
											<i class="glyphicon glyphicon-edit"></i>
										</div>
										<div class="col-xs-6 text-right">
											<p class="announcement-heading">${sessionScope.cagList.size()}</p>
											<p class="announcement-text">导航总数</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-3">
							<div class="panel panel-success">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-6">
											<i class="glyphicon glyphicon-edit"></i>
										</div>
										<div class="col-xs-6 text-right">
											<p class="announcement-heading">${sessionScope.artList.size()}</p>
											<p class="announcement-text">文章总数</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-3">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-6">
											<i class="glyphicon glyphicon-envelope"></i>
										</div>
										<div class="col-xs-6 text-right">
											<p class="announcement-heading">${sessionScope.comList.size()}</p>
											<p class="announcement-text">评论总数</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</c:if>
<jsp:include page="frame/Footer.jsp"></jsp:include>