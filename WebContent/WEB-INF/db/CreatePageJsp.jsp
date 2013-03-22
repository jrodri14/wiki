<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CREATE PAGE DATABASE </title>
</head>
<body>




<form action='CreatePageServlet' method='POST'>
<table style='text-align: left;' border='1' cellpadding='2' cellspacing='2'>
<tbody>
<tr>
<td>Path:</td>
<td> ${path} </td>
</tr>
<tr> 
<td>Author :</td>
       
       <td> ${account.name}</td>
       </tr>
       <tr> 
       <td style='vertical-align: top;'>Content:</td>
       <td style='text-align: left;'><textarea cols='40' rows='5' name='content'></textarea><br>

       </td>
       </tr>
       </tbody>
       </table> 
        
        
       <input type='submit' name='add' value='Create' />

       </form> 

</body>
</html>