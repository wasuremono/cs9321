<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<style type="text/css">
</style>
<%@ include file="header.html" %>

  <script>
  $(function() {
    $( ".datepicker" ).datepicker({dateFormat: "dd-mm-yy"});
  });
  </script>
</head>
<body>
<div class = "citySelect">
	<form action="ModifyCartServlet" method = "get">
		City 
			<select name = "City" onchange="" size="1">
				<option value ="Adelaide">Adelaide</option>
				<option value ="Brisbane">Brisbane</option>
				<option value ="Darwin">Darwin</option>
				<option value ="Hobart">Hobart</option>
				<option value ="Sydney">Sydney</option>
				<option value ="Melbourne">Melbourne</option>
			</select>
	
</div>
<div class="roomParameter" style="float:left;">

		Checkin Date <input name="checkin" type="text" class="datepicker" required>

	
		Checkout Date <input name="checkout" type="text" class="datepicker" required>
</div>
<div style="clear:both;">
Number of Rooms
	<input type="number" name="numRooms" min="1" required>


Maximum Price($ Per Room)
	<input type="number" name="budget" min="1">
	<Input Type = "submit" Value = "Search!">
</form>
</div>		
</body>
</html>