<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="library.model.*"%>
<%@ page import="java.util.Set" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="CSS/libraryStyle.css" type="text/css">
</head>
<%
	Book book = (Book) request.getAttribute("book");
%>
<body>
	<table title="Book Information">
		<tr>
			<th>Book Name</th>
			<th>ISBN</th>
			<th>Author's Name</th>
		</tr>
		<tr>
			<td><%=book.getName()%></td>
			<td><%=book.getISBN()%></td>
			
			<%
				for (Author author : book.getAllAuthors()) {

			%>

			<td><a href="http://localhost:8080/Library/library?requestType=author&authorId=<%=author.getId()%>"><%=author.getLastName()%> <%=author.getFirstName()%></a></td>
		<%
			}
		%>
		</tr>
		
		<tr>
			<td colspan="3"><a href="http://localhost:8080/Library/library?requestType=bookList">All books</a></td>
		</tr>
		<tr>
			<td colspan="3"><a href="http://localhost:8080/Library/library?requestType=authorList">All authors</a></td>
	
	</table>


</body>
</html>