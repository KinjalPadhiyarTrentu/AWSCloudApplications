<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String path = request.getParameter("imgname");
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("nameofperson");
	%>
	<table border="solid black">
		<tr>
			<td>ApplicationID:</td>
			<td> <%out.println(id); %>
		</tr>
		<tr>
			<td>Name:</td>
			<td> <%out.println(name); %>
		</tr>
		<tr>
			<td>Image:</td>
			<td><img src="<%=request.getContextPath()%>/<%=path%>" alt="Smiley face" height="200" width="200"></td>
			
		</tr>
	</table>
</body>
</html>