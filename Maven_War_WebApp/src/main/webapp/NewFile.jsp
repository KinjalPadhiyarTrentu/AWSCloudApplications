<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complain Booking!</title>
<script>
  var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };
</script>
</head>
<body>
<%
Random t = new Random();
	int x=t.nextInt(1000);
%>
<link type="text/css" rel="stylesheet" href="container.css" />
<div class="container">  
  <form id="contact" action="Up1"  method="post" enctype="multipart/form-data">
  
    <h3 align="center">Missing Person's Form</h3>
    <fieldset>
      <input placeholder="Application ID" name="appid" type="email" tabindex="2" value=<%=x%> readonly="readonly">
    </fieldset>
    <fieldset>
      <input placeholder="Your name" name="name" type="text" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
   	<center><input type="file" accept="*.png,*.jpg" name="imagefile" onchange="loadFile(event)"></center>
    </fieldset>
    <fieldset>
    Preview:  (Only Image Will Be Shown)
    </fieldset>
    <fieldset>
    <img id="output" height="200" width="350"/>
    </fieldset>
    
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
    <fieldset>
		<center><a href="getdata.jsp">Want To Retrieve Data?! </a></center>
    </fieldset>
    
    <p class="copyright">Designed by <a href="https://colorlib.com" target="_blank" title="Colorlib">VrajShah</a></p>
  </form>
</div>
</body>
</html>