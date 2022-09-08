<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

	Class.forName("com.mysql.jdbc.Driver");
	String url="jdbc:mysql://root1.cpk7vxbqxrsq.us-west-2.rds.amazonaws.com/Rohan_RDS";
	Connection con= DriverManager.getConnection(url,"root1","rootroot");
	Statement st = con.createStatement();
	ResultSet rs=st.executeQuery("select * from Information");
	ArrayList ls= new ArrayList();
	while(rs.next())
	{
		ls.add(rs.getString("ApplicationId"));
	}
	int y = ls.size();
%>
<link type="text/css" rel="stylesheet" href="container.css" />
<div class="container">  
<form id="contact" action="DisplayData">
  
    <h3 align="center">Retrieve Data </h3>
    <fieldset><%-- 
      <input placeholder="Enter Application ID" name="search" type="text" tabindex="1" value=<%=y %>> --%>
      <select name="search">
      <%
      	for(int x=0;x<y;x++)
      	{
      	%>
      	<option>
      	<% 
      		out.println(ls.get(x));
      	%>
      	</option>
      	<%
      	}
      	
      %>
      </select>
    </fieldset>
     <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
  
</form>
</div>
</body>
</html>