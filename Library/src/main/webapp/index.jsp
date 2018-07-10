<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href = "CSS/libraryStyle.css"  type="text/css">
</head>
<body>
	<tr>
		<td><a href="http://localhost:8080/Library/library?requestType=bookList">All books</a></td>
	</tr>
	<br>
	<tr>
		<td><a href="http://localhost:8080/Library/library?requestType=authorList">All authors</a></td>
	</tr>
	<br>
	<tr>
		<td><a href="http://localhost:8080/Library/newAuthor.jsp">Add new author</a></td>
	</tr>
	<tr>
		<td><a href="http://localhost:8080/Library/library?requestType=newBook">Add new book</a></td>
	</tr>
	<tr>
		<td><a href="http://localhost:8080/Library/search.jsp">Search</a></td>
	</tr>
</body>
</html>