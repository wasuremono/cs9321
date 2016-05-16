<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<style>.invalid::after { content: "Please correct/fill in this field."; background-color: red; }</style>

<form id="searchform" action="">
  <table>
  <tbody>
    <tr>
      <th>check in date</th>
      <td><input type="date" name="search_checkin"></td>
    </tr>
    <tr>
      <th>check out date</th>
      <td><input type="date" name="search_checkout"></td>
    </tr>
    <tr>
      <th>city</th>
      <td><select name = "search_city">
				<option value ="Adelaide">Adelaide</option>
				<option value ="Brisbane">Brisbane</option>
				<option value ="Darwin">Darwin</option>
				<option value ="Hobart">Hobart</option>
				<option value ="Sydney">Sydney</option>
				<option value ="Melbourne">Melbourne</option>
	  </select></td>
    </tr>
    <tr>
      <th>number of rooms</th>
      <td><select name="search_numberofrooms">
  			<option value="1">1</option>
  			<option value="2">2</option>
  			<option value="3">3</option>
  			<option value="4">4</option>
  			<option value="5">5</option>
  			<option value="6">6</option>
  			<option value="7">7</option>
  			<option value="8">8</option>
  			<option value="9">9</option>
  			<option value="10">10</option>
	</select></td>
    </tr>
    <tr>
      <th>maximum price</th>
      <td><input type="number" name="search_price"></td>        
    </tr>
    <tr>
      <td><input id="search_submit" type="submit" value="search"></td>
    </tr>
  </tbody>
  </table>
</form>
<div id="search_results"></div>

<script src="https://code.jquery.com/jquery-1.12.3.min.js" integrity="sha256-aaODHAgvwQW1bFOGXMeX+pC4PZIPsvn2h1sArYOhgXQ=" crossorigin="anonymous"></script>
<script>
(function() {
	var checkin = document.getElementsByName('search_checkin')[0];
	var checkout = document.getElementsByName('search_checkout')[0];
	var city = document.getElementsByName('search_city')[0];
	var numberofrooms = document.getElementsByName('search_numberofrooms')[0];
	var price = document.getElementsByName('search_price')[0];
	
	var invalidClassApply = function(elem, bool) {
	  if(bool) {
	    elem.parentNode.className = "invalid"; 
	    return true;
	  } else {
	  	elem.parentNode.className = ""; 
	  	return false;
	  }
	}

	var validateSearch = function(event) { 
		var date = new Date();
		var input1 = invalidClassApply(checkin, checkin.value === "" || (checkin.value.split('-')[2] <= date.getUTCDate() || checkin.value.split('-')[1] < date.getUTCMonth() || checkin.value.split('-')[0] < date.getUTCFullYear()));
		var input2 = invalidClassApply(checkout, checkout.value === "" || (checkout.value.split('-')[2] <= checkin.value.split('-')[2] || checkout.value.split('-')[1] < checkin.value.split('-')[1]) || checkout.value.split('-')[0] < checkin.value.split('-')[0]);
		var input3 = invalidClassApply(price, price.value <= 0);
	    
		var params = {
			checkinday: checkin.value.split('-')[2],
			checkinmonth: checkin.value.split('-')[1],
			checkinyear: checkin.value.split('-')[0],
			checkoutday: checkout.value.split('-')[2],
			checkoutmonth: checkout.value.split('-')[1],
			checkoutyear: checkout.value.split('-')[0],
			city: city.value,
			numberofrooms: numberofrooms.value,
			price: price.value
		};
		
	    if(!input1 && !input2 && !input3) {
			$.get("Search", params,  function(res) {
				$('#search_results').replaceWith(res);
				$('#search_submit')[0].value = "search again";
			});
		}
	    
		event.preventDefault();
	    return false;
	};
	
	document.forms['searchform'].addEventListener("submit", validateSearch);
})();
</script>