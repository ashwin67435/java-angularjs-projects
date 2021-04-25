<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="flightapp1" lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>admin</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="app.js"></script>
</head>

<body ng-controller="AdminCtrl">

	<% 
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		
		if(session.getAttribute("isadmin")=="false")
			response.sendRedirect("login.html");
	
		if(session.getAttribute("userName")==null)
		response.sendRedirect("login.html");
	%>
	
    <div>
        <h1>Flight Administration</h1>
        <span>Welcome,${userName}</span>
        <span><a href="">change password</a></span>
        <form action="logout" method="post">
            <input type="submit" value="logout">
        </form>
    </div>
    <h2>Site Administration</h2>
    <ul>
        <li> <a href="" ng-click="showUser()">Users</a></li>
        <li> <a href="" ng-click="showFlight()">Flights</a></li>
    </ul>
    <div ng-show="showUsers">
    	<button type="button" ng-click="addUser()">Add User</button>
        <div>
            <table>
                <thead>
                    <th>UserId</th>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>DOB</th>
                    <th>Gender</th>
                    <th>Email</th>
                </thead>
                <tr ng-repeat="user in users">
                    <td><input type="text" ng-model="user.userId" ng-disabled="!enableEditUser[{{$index}}]"></td>
                    <td><input type="text" ng-model="user.userName" ng-disabled="!enableEditUser[{{$index}}]"></td>
                    <td><input type="text" ng-model="user.password" ng-disabled="!enableEditUser[{{$index}}]"></td>
                    <td><input type="date" max="{{maxDateForDOB}}" value="{{user.dob.slice(0,10)}}" ng-model="user.dob" ng-disabled="!enableEditUser[{{$index}}]"></td>
                   	<td><input type="radio" ng-model="user.gender" ng-disabled="!enableEditUser[{{$index}}]"  name="gender{{$index}}" value="male"><span>Male</span></span> <input type="radio" name="gender{{$index}}" ng-disabled="!enableEditUser[{{$index}}]" ng-model="user.gender" value="female"><span>Female</span></td>
                    <td><input type="email" ng-model="user.email" ng-disabled="!enableEditUser[{{$index}}]"></td>
                    <td>
                        <div>
                            <button ng-click="editUser($index)">edit</button>
                            <button ng-click="deleteUser($index)">delete</button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <button ng-click="submitUsers()">Submit</button>
    </div>
    <div ng-show="showFlights">
    <button type="button" ng-click="addFlight()">Add Flight</button>
        <table>
            <thead>
                <th>FlightName</th>
                <th>FlightNumber</th>
                <th>Fare</th>
                <th>Source</th>
                <th>Destination</th>
                <th>DepatureTime</th>
                <th>Stops</th>
                <th>Duration</th>
                <th>Seats</th>
                <th>Tickets</th>
                <th>Capacity</th>
                <th>isMealAvailable</th>
            </thead>
            <tr ng-repeat="flight in flights">
                <td><input type="text" ng-model="flight.flightName" size="12" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" ng-model="flight.flightNumber" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" min="0.00" max="10000.00" step="1.7" ng-model="flight.price" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="text" ng-model="flight.source" size="12" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="text" ng-model="flight.destination" size="12" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="datetime-local" value="{{flight.departureTime}}" ng-model="flight.departureTime" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" min="0" max="4" step="1" ng-model="flight.numberOfStops" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" min="100" max="500" ng-model="flight.duration" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" step="1" min="0" max="250" ng-model="flight.seatsBooked" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="number" min="0" max="20" step=1 ng-model="flight.ticketsBooked" ng-disabled="!enableEditFlight[{{$index}}]"> </td>
                <td><input type="number" min="0" max="250" step="1" ng-model="flight.capacity" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td><input type="checkbox" ng-model="flight.mealAvailable" ng-disabled="!enableEditFlight[{{$index}}]"></td>
                <td>
                    <div>
                        <button ng-click="editFlight($index)">edit</button>
                        <button ng-click="deleteFlight($index)">delete</button>
                    </div>
                </td>
            </tr>
        </table>
        <button ng-click="submitFlights()">Submit</button>
    </div>
</body>

</html>