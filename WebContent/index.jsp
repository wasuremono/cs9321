<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hotel.chain.DatabaseTool"%>
<%@ page import="java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<style type="text/css">
</style>
  <meta charset="utf-8">
  <title>Hotel Booking System</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

  <script>
  $(function() {
    $( ".datepicker" ).datepicker({dateFormat: "dd-mm-yy"});
  });
  </script>
</head>
<body>
<center>
<div class = "controls">
<h2>&lt;hotel name&gt; Booking System</h2>
<h4><a href="login.jsp">Login</a>&nbsp;&nbsp;<a href="signup.jsp">Sign up</a></h4>
</div>
<p>
<div class = "rooms">
<h3>Some rooms we have to offer</h3>
<table>
	<tr>
		<td>Preview</td>
		<td>Room Type</td>
		<td>City</td>
		<td>Price</td>
	</tr>
<% 
	Connection conn = DatabaseTool.getConnection();
	PreparedStatement psselA = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Adelaide' AND roomType = 'Single'");
	ResultSet rsA = psselA.executeQuery();
	PreparedStatement psselB = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Brisbane' AND roomType = 'Single'");
	ResultSet rsB = psselB.executeQuery();
	PreparedStatement psselH = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Hobart' AND roomType = 'Single'");
	ResultSet rsH = psselH.executeQuery();
	PreparedStatement psselM = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Melbourne' AND roomType = 'Single'");
	ResultSet rsM = psselM.executeQuery();
	PreparedStatement psselP = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Perth' AND roomType = 'Single'");
	ResultSet rsP = psselP.executeQuery();
	PreparedStatement psselS = conn.prepareStatement("SELECT * FROM rooms WHERE location = 'Sydney' AND roomType = 'Single'");
	ResultSet rsS = psselS.executeQuery();
%>
	<tr>
		<td>PICTURE</td>
		<td><%=rsA.getString("roomType") %></td>
		<td><%=rsA.getString("location") %></td>
		<td><%=rsA.getInt("price") %></td>
	</tr>
	<tr>
		<td>PICTURE</td>
		<td><%=rsB.getString("roomType") %></td>
		<td><%=rsB.getString("location") %></td>
		<td><%=rsB.getInt("price") %></td>
	</tr>
	<tr>
		<td>PICTURE</td>
		<td><%=rsH.getString("roomType") %></td>
		<td><%=rsH.getString("location") %></td>
		<td><%=rsH.getInt("price") %></td>
	</tr>
	<tr>
		<td>PICTURE</td>
		<td><%=rsM.getString("roomType") %></td>
		<td><%=rsM.getString("location") %></td>
		<td><%=rsM.getInt("price") %></td>
	</tr>
	<tr>
		<td>PICTURE</td>
		<td><%=rsP.getString("roomType") %></td>
		<td><%=rsP.getString("location") %></td>
		<td><%=rsP.getInt("price") %></td>
	</tr>
	<tr>
		<td>PICTURE</td>
		<td><%=rsS.getString("roomType") %></td>
		<td><%=rsS.getString("location") %></td>
		<td><%=rsS.getInt("price") %></td>
	</tr>


</table>
</div>

<p>
<div class = "citySelect">
	<form action="CartServlet" method = "get">
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
<p>
<div class="roomParameter">

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
</center>	
</body>
</html>