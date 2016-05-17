<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
th{
	text-align:left;
}
</style>
<%@ include file="header.html" %>
</head>
<body>
${bookingError}
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background: rgba(255,255,255,0.9);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Enter your pin</h4>
				</div>
				
				<div class="modal-body">
					<form class="form-horizontal" action="ManageBooking" method="post" id="ManageBooking">
						<div class="form-group">
							<label for="pin" class="col-sm-2 control-label">PIN</label>
							<div class="col-sm-10">
								<input required type="text" class="form-control" id="pin" name="pin">
							</div>
						</div>
						
						<c:if test = "${loginFailed == true }">
							<p>Pin incorrect, please try again.<p>
						</c:if>
						<div class="modal-footer">	
							<input name="bookingRef" value="${referenceID}" type = "hidden"/>						
							<input name="action" type="hidden" value="login"/>	
							<button type="submit" class="btn btn-success">Log in</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="container">
<c:if test = "${not empty confirm }">
	<c:choose>
		<c:when test="${empty cart}">
		                      <h1>Your booking has been cancelled</h1>    
		</c:when>
		<c:otherwise>
			<br>
			<table width = "900" class="table_view">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "100">
				<col width = "100">
				<col width= "100">
				<tr>
					<th>Location</th>
					<th>Room Type</th>
					<th>Checkin Date</th>
					<th>Checkout Date</th>
					<th>Number of rooms</th>
					<th>Extra Beds</th>
					<th>Cost</th>
					<th>Extra Room</th>
			
			<c:forEach items="${cart}" var="cart">
			<tr>
			<td>${cart.location}</td>
			<td>${cart.roomType}</td> 
			<td><fmt:formatDate value="${cart.checkin}" pattern="dd-MM-yyyy" /></td> 
			<td><fmt:formatDate value="${cart.checkout}" pattern="dd-MM-yyyy" /></td> 
			<td>${cart.numRooms}</td>
			<c:choose>
				<c:when test="${cart.roomType != 'Single'}">
				    <td>
				    <form action="ManageBooking" method = "get">
				    <input type="text" size ="1" name="extraBed" value=${cart.extraBed}>
				    <input type="hidden" name="booking_ID" value=${cart.id}>				    
				    <input type="hidden" name="ref_id" value=${referenceID}>
				    <input type="hidden" name="action" value="update">
				    <Input Type = "submit" Value = "Change">
				    </form>
					<!--<a href="CartServlet?action=update&booking_ID=${cart.id}&extraBed=${extraBed}">Change--></td>
				</c:when>
					
				<c:otherwise>
					<td>N/A</td>
				</c:otherwise>
			</c:choose>				
			<td>${cart.cost}</td>	
			<td><a href="ManageBooking?action=add&bookingID=${cart.id}&location=${cart.location}&roomType=${cart.roomType}&currRoom=${cart.numRooms}&refid=${referenceID}">Add Room</a>	
			</c:forEach>
			</table>
			<p> Total Cost : $${cost}</p>
			<a href="ManageBooking?action=remove&referenceID=${referenceID}">Cancel Booking </a>
			<c:if test="${not empty add}">
			<c:choose>
			<c:when test="${add == true}">	
			Keep changes or revert
			</c:when>
			<c:otherwise>
			No additional rooms available
			</c:otherwise>
			</c:choose>			
			</c:if>
			
		</c:otherwise>
	</c:choose>
	</c:if>	
	</div>

</body>
<input type="hidden" name="bookingid" value="${bookingID}">
<input type="hidden" name="add" value="${add}">
<input type="hidden" name="bookingConfirmed" value="${confirm}">
</html>
<script>
$(document).ready(function(){
    // Show the Modal on load
    var bookingConfirmed = $("#bookingConfirmed").attr("value");
   if(bookingConfirmed){
	   $('#loginModal').modal("show");
	   
   }
});
   </script>