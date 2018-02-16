<%@ page language="java" import="java.util.*"
	import="com.brad.blog.bean.*" import="com.brad.blog.impl.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

	<c:if test="${sessionScope.admin !=null }">


		<div class="container">

			<div class="row col-md-12">
				<div class="col-md-12">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a
								href="<%=basePath%>admin.html?action=sysArticalAdmin&adminId=${sessionScope.admin.getId()}">文章管理</a></li>
							<li class="active">新建文章</li>
						</ol>
					</div>

					<form class="form-horizontal" name="add_artical_form"
						action="<%=basePath %>article.html?action=save&adminId=${sessionScope.admin.getId()}"
						method="post">
						<div class="col-md-6">
							<div class="form-group">
								<label for="title">标题</label> <input class="form-control"
									id="name" name="title" type="text">
							</div>
							<div class="form-group">
								<label for="category">系统分类</label> <select class="form-control"
									id="subject" name="category">
									<c:choose>
										<c:when test="${cgList!=null && fn:length(cgList)>0 }">
											<c:forEach items="${cgList }" var="cg">
												<option value="${cg.id }">${cg.categoryName}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:out value="无分类"></c:out>
										</c:otherwise>
									</c:choose>
								</select>
							</div>

							<div class="form-group">
								<textarea class="form-control" id="message" name="summary"
									placeholder="摘要" rows="5"></textarea>
							</div>

							<div class="form-group">
								<textarea class="form-control" id="message" name="content"
									placeholder="文章内容" rows="5"></textarea>
							</div>

							<div class="form-group">
								<button id="contact-submit" type="submit"
									class="btn btn-primary input-medium pull-right">保存</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</c:if>

</div>

<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
	function isValidate(form) {
		var category_name = form.category_name.value;

		if (category_name == "") {
			alert("请填写分类名！");

			return false;
		}
		return true;
	}
</script>