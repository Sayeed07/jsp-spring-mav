<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<h1>Bismillaahir Rahmaanir Raheeem.</h1>

<c:if test="${not empty message}">
<h1>${message}</h1> 
</c:if>

<form action="<%=request.getContextPath()%>/registration/student/save" method="POST" enctype="multipart/form-data">
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
 <a href="<%=request.getContextPath()%>/registration/student/find-all">Show All Students</a>


<h1>Student List</h1>

<input type="text" id="givenInput" onkeyup="myFunction()"/>
<table border="1" id="searchTb">
<thead>
<th>Student ID</th>
<th>Student Name</th>
<th>Student Roll</th>
<th>Student Photo</th>
<th>Action</th>
</thead>
<tbody>
<c:forEach items="${students}" var="student">

<tr>
<td>${student.studentId}</td>
<td>${student.studentName}</td>
 <td><img src="<c:url value="/photos/"/>${student.photoName}" height="200px"; width="150px"/></td> 
<td>${student.studentRoll}</td>
<td>
<a href="<%=request.getContextPath()%>/registration/student/edit?studentId=${student.studentId}">Edit</a>
&nbsp;
<a href="<%=request.getContextPath()%>/registration/student/delete?studentId=${student.studentId}">Delete</a>

</td>
</tr>
</c:forEach>
</tbody>

</table> 

<script type="text/javascript">
function myFunction() {    
    var input, filter, table, tr, td, i,j;
    input = document.getElementById("givenInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("searchTb");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        for(j=0;j<tr.length;j++){
      td = tr[i].getElementsByTagName("td")[j];
      if (td) {
        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";    
          break;     
        } else {
          tr[i].style.display = "none";           
        }
       
      }
      }
    }   
  };
</script>

</body>
</html>