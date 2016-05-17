<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.html" %>

</head>
<body>  
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background: rgba(255,255,255,0.9);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Enter your details</h4>
				</div>
				
				<div class="modal-body">
					<form class="form-horizontal" action="login" method="post" id="login">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">Username</label>
							<div class="col-sm-10">
								<input required type="text" class="form-control" id="username" name="username">
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input required type="password" class="form-control" id="password" name="password">
							</div>
						</div>
						<c:if test = "${loginFailed == true }">
							<p>Username or password incorrect, please try again.<p>
						</c:if>
						<div class="modal-footer">							
							<input name="action" type="hidden" value="login"/>	
							<button type="submit" class="btn btn-success">Log in</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="background: rgba(255,255,255,0.9);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Enter your details</h4>
				</div>
				
				<div class="modal-body">
					<form class="form-horizontal" action="login" method="post" id="register">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">Username</label>
							<div class="col-sm-10">
								<input required type="text" class="form-control" id="username" name="username">
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input required type="password" class="form-control" id="password" name="password">
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input required type="email" class="form-control" id="email" name="email">
							</div>
						</div>
						<c:if test = "${error == true }">
						<p>Username has already been taken, please choose another.<p>
						</c:if>
						<div class="modal-footer">
							<input name="action" type="hidden" value="register"/>	
							<button type="submit" class="btn btn-success">Sign Up</button>							
							<button id="toggleLogin" class="btn btn-success">Sign In</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="modal fade" id="addRoom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content" style="background: rgba(255,255,255,0.9);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Add Room to Booking</h4>
				</div>
				
				<div class="modal-body">
					<form class="form-horizontal" action="CartServlet" method="get" id="addBooking">
						<div class="form-group">
							<label for="rooms" class="col-sm-6 control-label">Number of Rooms</label>
							<div class="col-sm-6">
								<input required type="text" class="form-control" id="addnumRooms" name="numRooms" value="" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="beds" class="col-sm-6 control-label">Extra Beds</label>
							<div class="col-sm-6">
								<input required type="hidden" class="form-control" id="addextraBed" name="extraBed" value ="0" >
							</div>
						</div>
						<div class="modal-footer">
							<div class="parameters">
								<input name="action" type="hidden" value="add"/>	
								<input id="location" name="location" type="hidden" value=""/>	
								<input id="checkin" name="checkin" type="hidden" value=""/>
								<input id="checkout" name="checkout" type="hidden" value=""/>	
								<input id="roomType" name="roomType" type="hidden" value=""/>	
							</div>
							<button type="submit" class="btn btn-success">Add Booking</button>		
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<div class="container">
	<div class="row">
	  <div class="col-md-3">
      <div class="well">
      <h3 align="center">Room Search</h3>
        <form action="SearchServlet" method="get" class="form-horizontal">
          <div class="form-group">
            <label for="location" class="control-label">Location</label>
            <select class="form-control" name="location" id="location">
              <option value ="Adelaide">Adelaide</option>
				<option value ="Brisbane">Brisbane</option>
				<option value ="Canberra">Canberra</option>
				<option value ="Darwin">Darwin</option>
				<option value ="Hobart">Hobart</option>
				<option value ="Melbourne">Melbourne</option>
				<option value ="Sydney">Sydney</option>	
				
            </select>
          </div>
          <div class="form-group">
            <label for="checkin" class="control-label">Checkin Date</label>
            <div class='input-group' id="checkin">
              <input required type="text" class="form-control date-picker" name="checkin"  aria-describedby="basic-addon1">
              <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
          </div>
          <div class="form-group">
            <label for="checkout" class="control-label">Checkout Date</label>
            <div class='input-group' id="checkout">
              <input required type="text" class="form-control date-picker2" name="checkout"  aria-describedby="basic-addon1">
              <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
          </div>
          <div class="form-group">
            <label for="maxprice" class="control-label">Max Price</label>
            <div class="input-group">
              <div class="input-group-addon" id="basic-addon2">$</div>
              <input type="text" class="form-control" id="maxprice" name="maxprice" aria-describedby="basic-addon1">
            </div>
          </div>  
          <input name="action" type="hidden" value="searchRoom"/>   
          <button type="submit" class="btn btn-success">Search</button>
        </form>
      </div>
    </div>
    <div class="col-md-9" style="float:right">
    	<c:choose>
	    	<c:when test="${empty searchResult}">
	    		<p>No rooms were found with your search parameters, please try again<p>
	    	</c:when>
	    	<c:otherwise>
		        <c:forEach var="result" items="${searchResult}">
		         <div class="resultRoom" id="${result.id}">
		         	<article class="search-result row">				   
					<div class="col-md-3">
						<a title="${result.roomType}" class="thumbnail"><img src="images/${result.roomType}.jpg" alt="${result.roomType}" /></a>
					</div>
					<div class="col-md-6 excerpet">
						<h3><a title="">${result.roomType} Room</a></h3>
						<h4><p>${result.location}</p></h4>
						<h4>Average Rate<small><p>$${result.price} per night</p></small></h4>						
						<c:if test="${result.numRooms != 0 }">
							<p><h4>${result.numRooms} rooms available</h4></p>
						</c:if> 	
						<input type="text" hidden name="numRooms" value="${result.numRooms}">	
						<input type="text" hidden name="roomType" value="${result.roomType}">
						<input type="text" hidden name="location" value="${result.location}"> 
						<input type="text" hidden name="id" value="${result.id}"> 	
						<input type="text" hidden name="checkin" value="${result.checkin}"> 
						<input type="text" hidden name="checkout" value="${result.checkout}"> 		
		               <!--   <span class="plus"><a title="derp"><i class="glyphicon glyphicon-plus"></i></a></span>-->
					</div>	
					
					<!-- 
					<div class="col-md-3">
						<ul class="meta-search">
							<li><i class="glyphicon glyphicon-calendar"></i> <span>02/15/2014</span></li>
							<li><i class="glyphicon glyphicon-time"></i> <span>4:28 pm</span></li>
							<li><i class="glyphicon glyphicon-tags"></i> <span>People</span></li>
						</ul>
					</div>
					 -->
					
					
					<span class="clearfix borda"></span>			
					</article>	
				</div>	
	        </c:forEach>
        </c:otherwise>
		</c:choose>
    </div>
  </div>

</div>
 <input type="hidden" id="loginFail" data-value="${loginFailed}">
 <input type="hidden" id="error" data-value="${error}">
 <input type="hidden" id="loggedIn" data-value="${sessionScope.u.id}">
 <input type="hidden" id="bookingConfirmed" data-value="${bookingConfirmed}">
<script>
$('#toggleLogin').click(function(){
	$('#registerModal').modal("hide");
	$('#loginModal').modal("show");
});
$(".resultRoom").click(function() {
	var loggedin = $("#loggedIn").attr("data-value");    	
	var numRooms = this.getElementsByTagName("input")[0].value;
	var roomType = this.getElementsByTagName("input")[1].value;
	var location = this.getElementsByTagName("input")[2].value;
	var id = this.getElementsByTagName("input")[3].value;  
	var checkin = this.getElementsByTagName("input")[4].value;  
	var checkout = this.getElementsByTagName("input")[5].value; 
    if(!loggedin){
	  $('#registerModal').modal("show");
    }else if(loggedin && checkin){ 
    	$('#addRoom').modal("show");
    	$(".parameters #location").attr('value',location);
    	$(".parameters #checkin").attr('value',checkin);
    	$(".parameters #checkout").attr('value',checkout);
    	$(".parameters #roomType").attr('value',roomType);
    	if(roomType != 'Single'){
    		$(".form-control #extrabed").attr('type',"text");
    	}
    }else{
    }
	  return false;
	});
$(document).ready(function(){
    // Show the Modal on load
    var error = $("#error").attr("data-value");
    var loginFail = $("#loginFail").attr("data-value");
    var bookingConfirmed = $("#bookingConfirmed").attr("data-value");
    if(error){ 
    	$('#registerModal').modal("show");
    }else if(loginFail){
    	$('#loginModal').modal("show");
    }
   if(bookingConfirmed){
	   alert("Your booking has been confirmed, please check your email.");
	   
   }
});
$('.date-picker').datepicker({minDate: '0',dateFormat: 'dd-mm-yy' , onSelect: function(dateStr) {
	        var min = $(this).datepicker('getDate'); 
	        if (min) {
	          min.setDate(min.getDate() + 1);
	        }
	        $('.date-picker2').datepicker('option', 'minDate', min || '0'); 
	    }});

	    
	    $('.date-picker2').datepicker({minDate: '0',dateFormat: 'dd-mm-yy' , onSelect: function(dateStr) {
	        var max = $(this).datepicker('getDate'); 
	        if (max) {
	          max.setDate(max.getDate() - 1);
	        }
	        $('.date-picker').datepicker('option', 'maxDate', max || '+1Y+6M');

	    }});
	    
	    
</script>
</body>
</html>