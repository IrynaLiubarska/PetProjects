<%@ page import="library.model.Book" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Iryna
  Date: 01.12.2015
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Title</title>
    <link rel="stylesheet" href="CSS/libraryStyle.css" type="text/css">
</head>
<body>
<H1>Book search</H1>
<FORM ACTION="library?requestType=search" METHOD="POST">
    Please enter the Book you will find:
    <BR>
    <INPUT TYPE="TEXT" NAME="name">
    <BR>
    Please enter the year of the Book you will find:
    <BR>
    <INPUT TYPE="TEXT" NAME="yearOfPrint">
    <BR>
    Please enter the ISBN of the Book you will find:
    <BR>
    <INPUT TYPE="TEXT" NAME="isbn">
    <BR>
    <INPUT TYPE="SUBMIT" value="Submit">
</FORM>
</body>
</html>
