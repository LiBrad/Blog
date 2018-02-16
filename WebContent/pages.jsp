<%@ page language="java" import="java.util.*" import="cap.bean.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
								<li><a href="article.html?action=catePage&cateId=${t.id}">${t.categoryName }</a></li>
							</c:forEach>
						</c:when>
					</c:choose>

				</ul>
			</div>
		</div>
	</nav>


	<div class="container">

		<div class="row">
			<div class="col-lg-8">

				<c:choose>

					<c:when test="${art != null }">
						<h2>${art.title }</h2>
						<p>
							<i class="icon-user"></i>${art.name }</a> | <i class="icon-calendar"></i>
							${art.publishTime} | 阅读 ${art.count } 次
						</p>
						<hr>
						<p>${art.content }</p>
					</c:when>
					<c:otherwise>
						<p>网络繁忙~请刷新页面~</p>
					</c:otherwise>
				</c:choose>

				<hr>

				<!-- the comment box -->
	<c:if test="${msg!=null }">
		<div class="container">
			<div class="alert alert-success">${msg }</div>
		</div>
		<c:remove var="msg" />
	</c:if>

	<c:if test="${error != null }">
		<div class="container">
			<div class="alert alert-success">${error }</div>
		</div>
		<c:remove var="error" />

	</c:if>
				<div class="well">
					<h4>评论：</h4>
					<form role="form" name="comment"
						action="comment.html?action=commit&&artId=${art.id }"
						method="post" onsubmit="return isValidate(comment)">
						<div class="form-group">
							<input type="text"  class="form-control" name="comm_name" id="name" placeholder="请输入名字（默认匿名用户）">
							<textarea class="form-control" rows="3" name="comment_content"placeholder="请输入评论"></textarea>
						</div>
						<button type="submit" class="btn btn-primary">提交</button>
					</form>
				</div>

				<hr>

				<c:choose>

					<c:when test="${pgList != null && fn:length(pgList) > 0 }">
						<c:forEach items="${pgList }" var="comm">
							<p>
								<i class="icon-user"></i>
								${comm.commName } | <i class="icon-calendar"></i>
								${comm.time }
							</p>
							<p>${comm.content }</p>

							<hr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>暂无评论</p>
					</c:otherwise>
				</c:choose>
				<div>
						<!-- pager -->
						<ul class="pager">
							<c:if test="${curPage > 1 }">
								<li class="previous"><a
									href="<%=basePath%>comment.html?action=readMe&artId=${art.id }&curPage=${curPage -1}">&larr;
										上一页</a></li>
							</c:if>
							<c:if test="${curPage < totalPages }">
								<li class="next"><a
									href="<%=basePath%>comment.html?action=readMe&artId=${art.id }&curPage=${curPage +1}">下一页
										&rarr;</a></li>
							</c:if>
						</ul>
					</div>
			</div>
			
			
			
			<div class="col-lg-4">
				<div class="well">
					<h4>所属导航</h4>
					<div class="row">
						<div class="col-lg-6">
							<ul class="list-unstyled">

								<c:if test="${category != null }">
									<li>${category.categoryName }</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
				<!-- /well -->


			</div>
		</div>



	</div>


	<jsp:include page="frame/Footer.jsp"></jsp:include>
	
		<script type="text/javascript">
			function isValidate(comment) {
				var comment_content = comment.comment_content.value;

				if (comment_content == "") {
					alert("请填写评论内容");

					return false;
				}

				return true;
			}
		</script>