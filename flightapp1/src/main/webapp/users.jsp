<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="flightapp1">
<head>
<meta charset="UTF-8">
<title>Flight App</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
<script src="users.js"></script> 

<link rel="stylesheet" type="text/css" href="users.css">
</head>
<body ng-controller="UserCtrl">

	<% 
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

		if(session.getAttribute("isadmin")=="true")
			response.sendRedirect("login.html");

		if(session.getAttribute("userName")==null)
		response.sendRedirect("login.html");
	%>
<div class="title">
<h1> Flight Booking Appication </h1>
</div>
<div class="history" ng-init="username='${userName}'">
<span> Welcome, {{username}}<form action="logout" method="post">
            <input type="submit" value="logout">
        </form>
         <a ng-click="history(username)" href="#!history">Booking history</a> </span>
         <a href="#!cancel" ng-click="history(username)">Cancel Ticket</a>
</div>



<div class="form-container">  
    <div class="search-flight-form">  
    	<span>Trip:</span> <input type="radio" ng-change="tripChanged()" name="trip" ng-model="tripMode" value="oneway"><span>One Way</span></span> <input type="radio" ng-change="tripChanged()"  ng-model="tripMode" name="trip" value="return"><span>Return</span><br><br>
  			<label>From : </label>  
         <input type="text" name="source" id="source" ng-model="source" ng-keyup="completeSource(source)" />  
         <ul ng-model="hidethissource" ng-hide="hidethissource">  
              <li ng-repeat="sourcedata in filterSource" ng-click="fillTextboxS(sourcedata)">{{sourcedata}}</li>  
         </ul>  
         
         <label>To :</label>
         <input type="text" name="destination" id="destination" ng-model="destination" ng-keyup="completeDestination(destination)">
         <ul ng-model="hidethisdestination" ng-hide="hidethisdestination">
         	<li ng-repeat="destinationdata in filterDestination" ng-click="fillTextboxD(destinationdata)">{{destinationdata}}</li>
         </ul>
         
         <label>Departure :</label>
        <input type="date" name="departureTime" value="{{minDateForDep}}" min="{{minDateForDep}}" max="{{maxDateForDep()}}" ng-model="departureTime"><br><br>
       <div ng-show="showTrip">
	       	<label>Return :</label>
	        <input type="date" name="returnTime" ng-model="returnTime" value="" min="{{minDateForDep}}"  max="{{maxDateForDep()}}"><br>{{returnTime}}
       </div>
       <br><span>Class:</span> <input type="radio" name="class" ng-model="class" value="Economy"><span>Economy</span></span> <input type="radio" ng-model="business" name="class" value="business"><span>Business</span><br><br>
       <label>Travellers : {{adultCount+childCount+infantCount}} passengers</label><br><br>
       <span>Adults(12+ yrs) : </span><button ng-disabled="adultCount>=10" ng-click="adultCount = adultCount + 1">+</button>&nbsp;{{adultCount}}&nbsp;<button ng-disabled="adultCount<=1" ng-click="adultCount=adultCount-1">-</button><br><br>
       <span>Children(2-11 yrs) :</span><button ng-disabled="chidCount>=10" ng-click="childCount = childCount+1">+</button>&nbsp;{{childCount}}&nbsp;<button ng-disabled="childCount<=0" ng-click="childCount=childCount-1">-</button><br><br>
        <button ng-disabled="adultCount<=0" ng-click="getSearchFlights()">search flights</button>
   
    </div>  
</div> 
<br>

<button ng-click="showFilters = ! showFilters">show Filters</button>

<div ng-show="showFilters" class="filters">
	<div class="prices">
		<p>Price Filter :</p>
		<span>Min Price :</span>
		<select name="minPrice" id="minPrice" ng-model="priceInfo.min">
		<option value="2000">2000 Rs</option>
		<option value="4000">4000 Rs</option>
		<option value="6000">6000 Rs</option>
		<option value="8000">8000 Rs</option>
		<option value="12000">12000 Rs</option>
		</select>
		
		<span>Max Price :</span>
		<select name="maxPrice" id="maxPrice" ng-model="priceInfo.max">
		<option value="4000">4000 Rs</option>
		<option value="6000">6000 Rs</option>
		<option value="8000">8000 Rs</option>
		<option value="12000">12000 Rs</option>
		<option value="18000">18000 Rs</option>
		</select>
	</div>

	<div class="Airlines">
		<p>Airlines :</p>
		<span ng-repeat="flightName in flightNames">
			<input type="checkbox" ng-model="flightName.isTrue">
			<label>{{flightName.name}}</label>
		</span>
	</div>
	
	<div class="departureTime">
		<p>DepartureTime:</p>
		<span ng-repeat="dep in departureTimes">
			<input type="checkbox" ng-model="dep.isTrue">
			<label>{{dep.range}}</label><br ng-if="$index==1">
		</span>
	</div>
	<div class="stops">
		
	</div>
	

</div>

<div class="flights" ng-show="showSearchedFlights">
	<h2>Flights Available</h2>
	<div ng-repeat="flight in userSearchedFlights | priceFilter:priceInfo | airlineFilter:flightNames" class="flightContainer">
		<div class="flight-name">
			<p>FlightName : {{flight.flightName}}</p>
			<p>FlightNumber :{{flight.flightNumber}}</p>
			<p>Stops: {{flight.numberOfStops}}</p>
		</div>
		<div class="route-source">
			<p>From: {{flight.source}}</p>
				<div class="timedep-source">
				<p>departureTime: {{flight.departureTime | time}}</p>
				<p>departureDate: {{flight.departureTime | dateString}}</p>
				</div>
		</div>
		<div class="image">
			<p class="small">{{flight.duration | convertToHr}}</p>
			<img src="https://cdn3.iconfinder.com/data/icons/business-and-commercial-set-25/512/left_rightarrow2-512.png" height="70px">
		</div>
		<div class="route-destination">
			<p>To: {{flight.destination}}</p>
				<div class="timearr-destination">
				<p>ArrivalTime: {{flight.departureTime | arrivalTime:flight.duration}}</p>
				<p>ArrivalDate: {{flight.departureTime | arrivalDate:flight.duration}}</p>
				</div>
		</div>
		<div class="fare">
			<p>{{flight.price}}Rs</p>
		</div>
		<div class="book-button">
			<a style="background-color: #d6d6d6;
    color: #0e0d0d;
    padding: 1em 1.2em;
    text-decoration: none;
    text-transform: uppercase;" class="booknow" href="#!booking" ng-click="getBookingDetails(flight)">Book now</a>
		</div>
	</div>
</div>


<div ng-view>

</div>
</body>
</html>