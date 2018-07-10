<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="library.model.*"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/libraryStyle.css" type="text/css">
</head>
<body>


	<form action="library?requestType=postNewBook" method="POST">
		Book Name: <input type="text" name="nameOfBook"> <br />
		ISBN: <input type="text" name="isbn" /> <br />
		Year of print: <input type="text" name="year"/><br/>
		<select name="authorsUserSelection" multiple="multiple">

			<%
				for (Author author : (Set<Author>) request.getAttribute("authors")) {
			%>
			<option value="<%=author.getId()%>"><%=author.getLastName()%></option>
			<%
				}
			%>
		</select> <input type="submit" value="Submit" />
	</form>

</body>
</html>