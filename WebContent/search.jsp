<%@ page language="java" import="java.util.*" import="cap.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="frame/Header.jsp"></jsp:include>
<body>
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.html">Brad 云博客</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav">
						<li><a href="<%=basePath%>index.html">网站首页</a></li>

						<c:choose>
							<c:when test="${total != null && fn:length(total) > 0}">
								<c:forEach items="${total }" var="t">
									<li><a href="article.html?action=catePage&cateId=${t.id }">${t.categoryName }</a></li>
								</c:forEach>
							</c:when>
						</c:choose>

					</ul>
				</div>
			</div>
			</nav>


	<div class="container">

		<div class="row">
			<div id="blog" class="col-lg-8">
				<h1>
					Brad Blog——<small>分享技术，赢得未来</small>
				</h1>
				
				<br>
				<h1>搜索结果</h1>
				<c:choose>
					<c:when test="${searchList != null && fn:length(searchList)>0 }">
						<c:forEach items="${searchList }" var="slist">

							<h3>
								<a
									href="<%=basePath%>comment.html?action=readMe&artId=${slist.id }"
									target="_blank">${slist.title }</a>
							</h3>
							<p>
								<i class="glyphicon glyphicon-user"></i> 
								 ${slist.name } | <i class="glyphicon glyphicon-calendar"></i>
								 ${slist.publishTime } |
								阅读 ${slist.count } 次
							</p>

							<p>${slist.summary }</p>
							<br>
							<a class="btn btn-primary"
								href="<%=basePath%>comment.html?action=readMe&artId=${slist.id }">Read
								More <span class="glyphicon glyphicon-chevron-right"></span>
							</a>
							<hr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<p>还没有写过文章哦，赶紧写吧~</p>

					</c:otherwise>
				</c:choose>
				<!-- pager -->
				<ul class="pager">
					<c:if test="${curPageStr > 1 }">
						<li class="previous"><a
							href="<%=basePath%>index.html?curPageStr=${curPageStr - 1}">&larr;
								上一页</a></li>
					</c:if>
					<c:if test="${curPageStr < totalPages }">
						<li class="next"><a
							href="<%=basePath%>index.html?curPageStr=${curPageStr + 1}">下一页
								&rarr;</a></li>
					</c:if>
				</ul>

			</div>

			<div class="col-lg-4">
				<div class="well">
					<h4>搜索站内文章</h4>
					<form name="search_form" action="<%=basePath%>article.html?action=search"
						method="post">
						<div class="input-group">
							<input type="text" class="form-control" name="q"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</form>
				</div>


				<div class="well">
					<h4>博文TOP10</h4>
					<div class="row">
						<div class="">
							<ul class="list-unstyled">
							<c:choose>
							<c:when test="${top != null && fn:length(top) > 0}">
							<c:forEach items="${top }" var="top" varStatus="status">							
								<li><a
									href="<%=basePath%>comment.html?action=readMe&artId=${top.id }"
									target="_blank">${top.title}</a></li>								
								</c:forEach>
								</c:when>
								<c:otherwise>
								<li>暂无排名，sorry......</li>							
								</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	<jsp:include page="frame/Footer.jsp"></jsp:include>