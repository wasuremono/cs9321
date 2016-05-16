<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="search_results">
    <c:if test="${results.length == 0}">
 			<p>Sorry, we were unable to find any rooms under those requirements, please try again.</p>
	</c:if>
	
	<c:if test="${results.length > 0}">
			<table><tbody>
	</c:if>
	<c:forEach items="${results}" var="result">
   			<tr><td>Room: ${result.getRoomId()} Type: ${request.getRoomTypeName()} At: ${request.getHotelLocation()} For:${request.getRoomPrice()}</td></tr>
	</c:forEach>
	<c:if test="${results.length > 0}">
			</tbody></table>
	</c:if>
</div>