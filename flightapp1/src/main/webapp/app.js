app = angular.module("flightapp1",[])

app.controller("AdminCtrl",AdminCtrl)


function AdminCtrl($scope,$http){

	//users
    $scope.users = null
    $scope.showUser = false
    
    //getUsers
    $scope.getUsers = function(){
        $http({
            url:"/flightapp1/userdetails",
            method:"GET",
        }).then(function(response){
            $scope.users = response.data;
        },function(error){
            console.log(error.data);
        });
    }
    
 	$scope.addedUserCount = 0;
    //addUser
    $scope.addUser = function(){
    	var usr = { 
    		userId:"",
    		userName:"",
    		email:"",
    		gender:"",
    		dob:"",
    		password:""
    	}
		$scope.users.push(usr);
		$scope.enableEditUser[$scope.users.length-1] = true;	
		$scope.addedUserCount += 1;
    }
    

    //editUser
    $scope.enableEditUser = [];
    
    $scope.editUser = function(index){
    
    	$scope.enableEditUser[index] = true;
    	
    }
    
    function isUserExists(user){
    	console.log($scope.users);
    }
    
    
    //submitUser
    $scope.submitUsers = function(){
    	
    	$http.post("/flightapp1/updateuser",angular.toJson($scope.users)).then(function(response){
    		console.log(response);
    	});
    	$scope.addedUserCount = 0;
    	//console.log(angular.toJson($scope.users));
    }
    
    //maxDOB
   	$scope.maxDateForDOB = new Date().toISOString().slice(0,10);
   
    //deleteUser
    $scope.deleteUser = function(index){
    	$scope.users.splice(index,1);
    }
    
    //showUsers
    $scope.showUser = function(){
    $scope.getUsers();
    	$scope.showUsers = $scope.showUsers == true ? false : true;
    }
    
    // ===========================================================================
    
    //flights
    $scope.flights = null
	$scope.showFlights = false
	
    //getFligths
    $scope.getFlights = function(){
        $http({
            url:"/flightapp1/flightdetails",
            method:"GET"
        }).then(function(response){
            $scope.flights = response.data;
        },function(error){
            console.log(error.data);
        });
    }
    //addFlights
    
    $scope.addFlight = function(){
    	var flt = {
    		"flightName":"",
    		"flightNumber":"",
    		"source":"",
    		"destination":"",
    		"price":"",
    		"departureTime":"",
    		"duration":"",
 	  		"numberOfStops":"",
 	  		"mealAvailable":false,
    	}
    	$scope.flights.push(flt);
		$scope.enableEditFlight[$scope.flights.length-1] = true;	
    }
    
    
    //editflights
    $scope.enableEditFlight = [];
    
    $scope.editFlight = function(index){
    
    	$scope.enableEditFlight[index] = true;
    	
    }
    
    //deleteFlights
    $scope.deleteFlight = function(index){
    	$scope.flights.splice(index,1);
    }
    
    //submitFlights
    $scope.submitFlights = function(){
    	
    	
    	$http.post("/flightapp1/updateflight",angular.toJson($scope.flights)).then(function(response){
    		console.log(response);
    	});
    	console.log($scope.flights);
    }
    
    //showFlights
    $scope.showFlight = function(){
    $scope.getFlights();
    	$scope.showFlights = $scope.showFlights == true ? false : true;
    }
    
}
