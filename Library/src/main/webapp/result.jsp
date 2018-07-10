<%@ page import="library.model.Author" %>
<%@ page import="library.model.Book" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Iryna
  Date: 06.12.2015
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="CSS/libraryStyle.css" type="text/css">

<body>
<table title="Books Information">
    <tr>
        <td colspan="3">Books info</td>
    </tr>

    <%
        for (Book book : (Set<Book>) request.getAttribute("books")) {
    %>

    <tr>
        <td><%=book.getName()%>
        </td>
        <td><%=book.getISBN()%>
        </td>

        <%
            for (Author author : book.getAllAuthors()) {

        %>

        <td>
            <a href="http://localhost:8080/Library/library?requestType=author&authorId=<%=author.getId()%>"><%=author.getLastName()%> <%=author.getFirstName()%>
            </a></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>

</table>

</body>
</html>
