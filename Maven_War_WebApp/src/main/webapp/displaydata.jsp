
<%@page import="S3Service.CreateBucket"%>
<%@page import="java.sql.*"%>
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
		String name=null;
		String imageurl=null; //which is actually key of the object
		String frompc=null;
		String x = request.getParameter("search");
		int p = Integer.parseInt(x);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://firstdbserver.c49uwwqcuz4t.us-east-2.rds.amazonaws.com/rds";
			Connection con = DriverManager.getConnection(url, "root", "rootroot");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Information where ApplicationID='" + p + "'");

			while (rs.next()) {
				name = rs.getString("Name");
				imageurl = rs.getString("ImageKey");
				System.out.println("in jsp"+imageurl);
				CreateBucket cr = new CreateBucket();
				System.out.println("In JSP: LATEST "+imageurl);
				cr.imagebykey(imageurl);
				//frompc = cr.getimage(imageurl);
					
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	%>
	
Name: <input type="text" value=<%=name %> readonly="readonly" />
ImageUrl: <input type="text" value=<%=imageurl %> readonly="readonly" />
<img src="C:/VRAJ/dn.jpg" alt="Smiley face" height="200" width="200">

</body>
</html>