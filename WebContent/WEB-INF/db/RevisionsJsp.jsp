<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REVISIONS  DATABASE</title>
</head>
<body>

<table border = 1>
<tr><th>Revisions</th><th>Revert</th></tr>

<c:forEach items = "${entries}" var = "edits" varStatus = "status">
<tr>
<td>

<a href = '/cs320stu19/wiki/${path}?revisionsId=${edits}'> revisions ${status.index} </a></td>
<td>
<form action='Revert' method='post'>
 <input type="hidden" name="id" value="${edits}" />
 <input type="hidden" name="path" value="${path}" />
 <input type = "submit" name = 'revert' value = "Revert" >
</form>
</td>

</tr>
</c:forEach>

</table>


</body>
</html>
