app = angular.module("flightapp1", ["ngRoute"]);

app.controller("UserCtrl", UserCtrl);

app.config(function($routeProvider){
	$routeProvider.when("/booking",{
		templateUrl:"booking.html",
		controller:"UserCtrl",
	})
	.when("/payment",{
		templateUrl:"payment.html",
		controller:"UserCtrl",
	})
	.when("/history",{
		templateUrl:"bookedTickets.html",
		controller:"UserCtrl",
	})
	.when("/cancel",{
		templateUrl:"cancelTickets.html",
		controller:"UserCtrl",
	})
})

app.service('ticketService',function(){
	var ticket;
	
	return {
		getTicket:getTicket,
		setTicket:setTicket,
	}
	
	function getTicket(){
		return ticket;
	}
	
	function setTicket(value){
		ticket = value;
	}
	
})

app.service('searchedQuery',function(){
	var search;
	
	return {
		getResult:getResult,
		setResult:setResult
	}
	function getResult(){
		return search;
	}
	
	function setResult(value){
		search = value;
	}	
	
})

app.service('flightService',function(){
	var flight;
	return {
		getFlight:getFlight,
		setFlight:setFlight,
	};
	
	function getFlight(){
		return flight;
	}
	
	function setFlight(value){
		flight = value;
	}
})

app.filter('priceFilter',function(){
    return function(flights,priceInfo){
        var filtered = [];
        var min = priceInfo.min;
        var max = priceInfo.max;
        angular.forEach(flights,function(flight){
            if(flight.price >= min && flight.price <= max){
                filtered.push(flight)
            }
        })
        return filtered;
    }
});

app.filter('airlineFilter',function(){
    return function(flights,flightNames){
        var filtered = [];
        for(var index = 0; index < flights.length;index++){
            for(var jndex = 0; jndex < flightNames.length;jndex++){
                if(flights[index].flightName == flightNames[jndex].name && flightNames[jndex].isTrue == true){
                    filtered.push(flights[index]);
                }
            }
        }
        return filtered;
    }
});

app.filter('time',function(){
    return function(input){
        return input.slice(11,16);
    }
});

app.filter('dateString',function(){
    return function(input){
        return new Date(input.slice(0,10)).toDateString();
    }
});

app.filter('capitalize',function(){
	return function(input){
		return input.charAt(0).toUpperCase()+input.slice(1);
	}
})

app.filter('arrivalTime',function(){
    return function(input,duration){
       var dateObj = new Date(input);
       dateObj.setMinutes(dateObj.getMinutes()+duration);
       return dateObj.toISOString().slice(11,16);
    }
});


app.filter('arrivalDate',function(){
    return function(input,duration){
        var dateObj = new Date(input);
        dateObj.setMinutes(dateObj.getMinutes()+duration);
        return dateObj.toDateString();
    }
});

app.filter('convertToHr',function(){
    return function(input){
        var dur = input;
        var hr = dur/60;
        var hours = Math.floor(hr);
        var min = (hr-hours)*60;
        var minutes = Math.round(min);
        if(minutes==0){
        	return hours+"hr";
        }else{
        	return hours+"hr"+minutes+"min";
        }
        
    }
});

app.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);

        for (var i = 0; i < total; i++) {
            input.push(i);
        }

        return input;
    };
});


function UserCtrl($scope, $http,$location,$window,$filter,flightService,searchedQuery,ticketService) {


    $scope.getPlaces = function() {

        $http({
            url: "/flightapp1/places",
            method: "GET",
        }).then(function(response) {
            $scope.sourceList = response.data;
            $scope.destinationList = response.data
        }, function(error) { console.log(error.data) });

    }

    $scope.sourceList = $scope.getPlaces();

    $scope.completeSource = function(string) {
        $scope.hidethissource = false;
        var outputsource = [];
        angular.forEach($scope.sourceList, function(source) {
            if (source.toLowerCase().indexOf(string.toLowerCase()) >= 0) {
                outputsource.push(source);
            }
        });
        $scope.filterSource = outputsource;
    }


    $scope.fillTextboxS = function(string) {
        $scope.source = string;
        $scope.hidethissource = true;
    }


    //destination


    $scope.completeDestination = function(string) {
        $scope.hidethisdestination = false;
        var outputdestination = [];
        angular.forEach($scope.destinationList, function(destination) {
            if (destination.toLowerCase().indexOf(string.toLowerCase()) >= 0) {
                outputdestination.push(destination);
            }
        });
        $scope.filterDestination = outputdestination;
    }

    $scope.fillTextboxD = function(string) {
        $scope.destination = string;
        $scope.hidethisdestination = true;
    }

    //searchflights based on S & D
    $scope.adultCount = 1;
    $scope.childCount = 0;
    $scope.infantCount = 0;
    $scope.userSearchedFlights = [];
    $scope.minDateForDep = new Date().toISOString().slice(0,10);
    $scope.showTrip = false;
    
    $scope.flightNames = [
    	{name:"Spice",isTrue:true},
    	{name:"Indi Go",isTrue:true},
    	{name:"Deccan Air",isTrue:true},
    ];
    
    $scope.departureTimes =[
    	{range:"00:00 - 06:00",text:"Early Morning",isTrue:false},
    	{range:"06:00 - 12:00",text:"Morning",isTrue:false},
		{range:"12:00 - 18:00",text:"Mid Day",isTrue:false},
		{range:"18:00 - 24:00",text:"Night",isTrue:false},    	
    ]
    
    

    
    $scope.priceInfo = {
    	min:2000,
    	max:18000,
    }
    
    $scope.tripChanged = function(){
    	if($scope.tripMode == "return"){
    		$scope.showTrip = true;
    	}else{
    		$scope.showTrip = false;
    	}
    }
    
    $scope.maxDateForDep = function(){ 
    	var date = new Date();
    	date.setDate(date.getDay()+180);
    	return date.toISOString().slice(0,10);
    	
    	}
	$scope.showFilters = false
	$scope.showSearchedFlights = false
	
	function convertToISOString(date){
		
    	return $filter('date')(date, "yyyy-MM-dd")
	}
	
	
    $scope.getSearchFlights = function() {
 
    	$scope.departureTime = convertToISOString($scope.departureTime);
    	console.log($scope.departureTime)
    	if($scope.returnTime === undefined){
    		$scope.returnTime="";
    	}else{
    		$scope.returnTime = convertToISOString($scope.returnTime);
    	}	
    	
		console.log("flightdetailssearch?source=" + $scope.source + "&destination=" + $scope.destination +"&departureTime="+$scope.departureTime +"&travellersCount="+($scope.adultCount+$scope.childCount+$scope.infantCount) +"&returnTime="+$scope.returnTime);
        $http({
            url: "flightdetailssearch?source=" + $scope.source + "&destination=" + $scope.destination +"&departureTime="+$scope.departureTime +"&travellersCount="+($scope.adultCount+$scope.childCount+$scope.infantCount) +"&returnTime="+$scope.returnTime,
            method: "GET",
        }).then(function(result) { console.log(result.data); $scope.userSearchedFlights = result.data ; $scope.showSearchedFlights = true }, function(result) { console.log(result.data) });
		
		queryData = {
			from:$scope.source,
			to:$scope.destination,
			departure:$scope.departureTime,
			class:$scope.class,
			adults:$scope.adultCount,
			child:$scope.childCount,
			infant:$scope.infantCount,
		}
		searchedQuery.setResult(queryData);
		$scope.queryObj = searchedQuery.getResult();
		console.log($scope.queryObj);
		
    }
    $scope.getBookingDetails = function(flight){
    	flightService.setFlight(flight);
    	$scope.flightObj = flightService.getFlight();
    	console.log($scope.flightObj);
    }
    $scope.showpass = false
    $scope.passengers = [];
    
    $scope.addPass = function(name,age){
    	$scope.passengers.push({name:name,age:age});
    }
    
    $scope.deletePass = function(index){
    	$scope.passengers.splice(index,1);
    }
    $scope.submitPassengers = function(){
    	$scope.showpass = true;
    }

	$scope.ticketDetails = ticketService.getTicket();
  	
 
  	
  	$scope.cancelBooking = function(ticket){
  		var isOK = confirm("Are you sure to cancel the ticket");
  		if(isOK == true){
  			ticket.isCancel = true;
  			$http({
            url: "/flightapp1/cancelticket?bookingId="+ticket.bookingId,
            method: "GET",
        }).then(function(result){
        
        $scope.histories = result.data
        console.log(result.data);
        },function(error){console.log(error.data)});
  		}
  		
  	}
  	
  	$scope.cancelTicket = function(ticket){
  		var isOK = confirm("Are you sure to cancel the ticket");
  		if(isOK == true){
  			ticket.canceled = true;
  			$http({
            url: "/flightapp1/cancelticket?bookingId="+ticket.bookingId,
            method: "GET",
        }).then(function(result){
        
        $scope.histories = result.data
        console.log(result.data);
        },function(error){console.log(error.data)});
  			
  		}
  	}
  	
  	$scope.history = function(username){
  		 $http({
            url: "/flightapp1/tickethistory?userName="+username,
            method: "GET",
        }).then(function(result){
        
        $scope.histories = result.data
        console.log(result.data);
        },function(error){console.log(error.data)})
  	}
  	
    $scope.payment = function(){
    	$scope.totalFares = ($scope.queryObj.adults +  $scope.queryObj.child) * $scope.flightObj.price;
    	
    	
    	$scope.bookedTicket = {
    		bookingId:Math.random()*100+"",
    		flightName:$scope.flightObj.flightName,
    		flightNumber:$scope.flightObj.flightNumber,
    		user:$scope.username,
    		passengers : $scope.passengers,
    		fare : $scope.totalFares.toFixed(2),
    		source : $scope.flightObj.source,
    		destination: $scope.flightObj.destination,
    		departure:$scope.flightObj.departureTime,
    		class:$scope.queryObj.class,
    		isCancel:false,
    	}
    	ticketService.setTicket($scope.bookedTicket);
    	
    	
    	$http.post("/flightapp1/newticket",angular.toJson($scope.bookedTicket)).then(function(response){
    		console.log(response);
    	});
    	console.log($scope.username);
    	console.log($scope.bookedTicket);
    	$location.path("/payment");
    }
    
    
}