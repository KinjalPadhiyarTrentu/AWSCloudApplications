<%@page import="java.util.List"%>
<%@page import="abc.getkeypair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
	getkeypair g = new getkeypair();
	List ls = g.getkeylist();
	pageContext.setAttribute("x", ls);
%>

<form action="firstpage">

ID: <input type="text" name="iid" /> <br>

Key-Pair: 
		<input type="radio" name="r1" value="exist"> Existing <br>
		<input type="radio" name="r1" value="new"> Generate New <br>
		
Enter Name: <input type="text" name="nameofi"> <br>
		
		<br> 
		<br>
		<br>
Name of existing keypairs:(Select One)
		<select name="selectedkey">
		
			<c:forEach items="${x}" var="item">
				<option value="${item}">
				${item }
				</option>
			</c:forEach>
		</select>
		
<input type="submit">

</form>
			 
</body>
</html>