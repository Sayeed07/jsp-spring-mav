<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bismillah</h1>
	<a href="<%=request.getContextPath()%>/registration/teacher/find-all">Go
		to Student Page</a>
	<form action="<%=request.getContextPath()%>/j_spring_security_check"
		method="post">

		<label>UserName:</label> <input type="text" name="j_username" /><br />

		<label>Password:</label> <input type="passowrd" name="j_password" /><br />
		<input type="submit" value="Login" /> <span id="msg" style="color: red">
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</c:if>
		</span>
	</form>
	
	<script type="text/javascript">	
		document.getElementById("msg").style.display="block";		   
		 setTimeout(function() {document.getElementById("msg").style.display="none";},2000); 		 
 </script>
	
	
</body>
</html>