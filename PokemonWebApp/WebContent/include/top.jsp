
<%
	if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
		response.sendRedirect("login.jsp");
	}
%>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/font-awesome.css">
<link rel="stylesheet" href="css/style.css">