<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
</head>
<body>
<center><h1>Create Account</h1></center>
<pre style="color: red;">
<c:out value="${err}" />
</pre>
<form action= "CreateAccount" method="post">
<table border='1'>
<tr><td>UserName:</td><td><input type='text' name='username' value = "${username}"required /></td></tr>
<tr><td>Password:</td><td><input type='password' name='password' value = "${password}"  required /></td></tr>
<tr><td>Retype Password:</td><td><input type='password' name='password2'  required /></td></tr>
<tr><td>First Name:</td><td><input type='text' name='firstname' value = "${firstname}" required /></td></tr>
<tr><td>Last Name:</td><td><input type='text' name='lastname' value = "${lastname }" required /></td></tr>
<tr><td>E-Mail:</td><td><input type='text' name='email' value = "${email }" required /></td></tr>
</table><br />
<input type='submit' name='create' value='CreateAccount' />
</form>
</body>
</html>