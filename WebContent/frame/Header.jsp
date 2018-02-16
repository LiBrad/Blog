<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Brad Yun Blog</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="<%=basePath%>bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath%>bootstrap/css/bootstrap-theme.css"
	rel="stylesheet">


<link href="<%=basePath%>bootstrap/css/blog-home.css" rel="stylesheet">
<script src="<%=basePath%>bootstrap/js/jquery-2.1.1.js"></script>
</head>

<body>
	<div id="wrapper">
		