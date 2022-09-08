<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<form action="getName"  method="post" enctype="multipart/form-data">
	Enter UserName: <input type="text" name="un">
	Enter Name: <input type="text" name="fn">
	<input type="file" accept="*.png,*.jpg" name="imagefile" onchange="loadFile(event)">
	<img id="output" height="200" width="350"/>
	<input type="submit">
</form>

<a href="fetchObject.jsp"> Want to retrieve data?</a>
</body>
</html>