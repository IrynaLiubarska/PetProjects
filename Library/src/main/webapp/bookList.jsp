<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="library.model.*"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href = "CSS/libraryStyle.css"  type="text/css">

</head>
<body>


	<table title="All Books Information">
		<tr>
			<td colspan="2">All books</td>
		</tr>

		<%
			for (Book book : (Set<Book>) request.getAttribute("books")) {
		%>

		<tr>
			<td><a href="http://localhost:8080/Library/library?requestType=book&bookId=<%=book.getId()%>"><%=book.getName()%></a></td>
			<td><%=book.getISBN()%></td>
		</tr>
		<%
			}
		%>

	</table>

</body>
</html>