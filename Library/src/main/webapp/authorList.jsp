<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<table title="All Authors Information">
		<tr>
			<td>Author's First Name</td>
			<td>Author's Last Name</td>
		</tr>

		<%
			for (Author author : (Set<Author>) request.getAttribute("authors")) {
		%>

		<tr>
			<td><%=author.getFirstName()%>
			</td>


			<td><a
				href="http://localhost:8080/Library/library?requestType=author&authorId=<%=author.getId()%>"><%=author.getLastName()%></a></td>
		</tr>

		<%
			}
		%>

	</table>

</body>
</html>