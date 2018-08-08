<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>
</head>
<body style="margin:0; padding:0; border:0">
<%@include file="sidebar.jsp" %>
<div style="right:0; top:0; position:absolute">
<a href="<%=request.getContextPath()%>/j_spring_security_logout">
<img src="<c:url value="/photos/"/>user.png" width="50px"; height="50px" style="border-radius:50%; margin:0px 0px" />
</a>
</div>
</body>
