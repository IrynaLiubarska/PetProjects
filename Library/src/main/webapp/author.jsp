<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="library.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="CSS/libraryStyle.css" type="text/css">
</head>

<%
    Author author = (Author) request.getAttribute("author");
%>

<body>
<table title="Author Information">
    <tr>
        <td colspan="2"><%=author.getFirstName()%> <%=author.getLastName()%></td>
    </tr>
    <tr>
        <td>Book Name</td>
        <td>ISBN</td>
    </tr>

    <%
        for (Book book : author.getAllBooks()) {
    %>

    <tr>
        <td><a href="http://localhost:8080/Library/library?requestType=book&bookId=<%=book.getId()%>"><%=book.getName()%></a></td>
        <td><%=book.getISBN()%></td>
    </tr>

    <%
        }
    %>

    <tr>
        <td colspan="2"><a href="http://localhost:8080/Library/library?requestType=bookList">All books</a></td>
    </tr>

    <td colspan="2"><a href="http://localhost:8080/Library/library?requestType=authorList">All authors</a></td>

</table>
</body>
</html>