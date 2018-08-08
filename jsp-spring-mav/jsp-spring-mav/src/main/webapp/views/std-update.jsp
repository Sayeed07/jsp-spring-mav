<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Bismillaahir Rahmaanir Raheeem.</h1>

<c:if test="${!empty message}">
<h1>${message}</h1> 
</c:if>

<form action="<%=request.getContextPath()%>/registration/student/update" method="POST" enctype="multipart/form-data">
<select name="teacherId"> 
<option label="Select Teacher" value=""/>
<c:forEach items="${teachers}" var="teacher">
<option label="${teacher.teacherName}" value="${teacher.teacherId}"/>
</c:forEach>
</select><br/>

<input type="hidden" name="studentId" value="${student.studentId}"/> 

<label>Student Name: </label><br/>
<input type="text" name="studentName" value="${student.studentName}"/><br/>

<label>Student Roll: </label><br/>
<input type="text" name="studentRoll" value="${student.studentRoll}"/><br/>

 <label>Photo: </label><br/>
<input type="file" name="photo" maxlength="20000"/><br/>

<input type="submit" value="Submit"/>

</form>


</body>
</html>