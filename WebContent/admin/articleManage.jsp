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
<div class="container">
	<div class="col-lg-12">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

			<div class="navbar-header">
				<a class="navbar-brand" href="<%=basePath%>admin/index.jsp">Brad
					Blog 后台管理</a>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li><a class="btn active" href="<%=basePath%>admin/index.jsp"><i
							class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
					<li><a class="btn active"
						href="<%=basePath%>admin.html?action=sysArticalAdmin&adminId=${sessionScope.admin.getId()}"><i
							class="glyphicon glyphicon-cog"></i> 文章管理</a></li>
					<li><a class="btn active"
						href="<%=basePath%>admin.html?action=sysCategoryAdmin"><i
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

	<c:if test="${msg!=null }">
		<div class="container">
			<div class="alert alert-success">${msg }</div>
		</div>
		<c:remove var="msg" />
	</c:if>

	<c:if test="${error!=null }">
		<div class="container">
			<div class="alert alert-success">${error }</div>
		</div>
		<c:remove var="error" />
	</c:if>

	<c:if test="${sessionScope.admin !=null }">
		<div class="container">
			<div class="btn-toolbar">
				<a class="btn btn-primary"
					href="<%=basePath%>article.html?action=add&adminId=${sessionScope.admin.getId()}">新建文章</a>
			</div>
			<div class="well">
				<table class="table table-hove table-responsive">
					<thead>
						<tr>
							<th>标题</th>
							<th>系统分类</th>
							<th>作者</th>
							<th>最近一次修改时间</th>
							<th style="width: 50px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${artList!=null }">
								<c:forEach items="${artList }" var="art">
									<tr>
										<td><a
											href="<%=basePath %>article.html?action=update&artId=${art.id}">${art.title }</a></td>
										<td>${art.cname }</td>
										<td>${art.name }</td>
										<td>${art.publishTime }</td>
										<td><a
											href="<%=basePath %>article.html?action=update&artId=${art.id}"><i
												class="glyphicon glyphicon-pencil"></i></a></td>
										<td><a
											href="${basePath }article.html?action=delete&artId=${art.id }&adminId=${sessionScope.admin.getId()}"><i
												class="glyphicon glyphicon-remove"></i></a></td>

									</tr>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:out value="暂时没有文章"></c:out>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div>
				<ul class="pager">
					<c:if test="${curPage > 1 }">

						<li class="previous"><a
							href="<%=basePath%>admin.html?action=sysArticalAdmin&adminId=${sessionScope.admin.getId()}&curPage=${curPage-1}">&larr;
								上一页</a></li>

					</c:if>
					<c:if test="${curPage < totalPages }">
						<li class="next"><a
							href="<%=basePath%>admin.html?action=sysArticalAdmin&adminId=${sessionScope.admin.getId()}&curPage=${curPage+1}">下一页
								&rarr;</a></li>
					</c:if>
				</ul>
			</div>


		</div>
	</c:if>

</div>

<jsp:include page="frame/Footer.jsp"></jsp:include>