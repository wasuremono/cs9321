<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
</head>
<body>
<c:choose>
	<c:when test="$(empty referenceID)">
		<center><h3>Error, please see email for further details on modifying booking.</h3></center>
	</c:when>
</c:choose>
<c:otherwise>
	<form action= "ModifyVerification" method = "get">
	Reference ID:
	<input type = "text" name = "refID" value="${referenceID}" readonly>
	<p>
	Verify PIN number
	<input type = "text"  size = "10" name = "pinNum" required>
	<p>
	<Input Type = "submit" Value = "Verify">
	
	</form>
</c:otherwise>		
</body>
</html>