var app = angular.module('myApp', []);
app.controller('reviewerCtrl', function($scope,$http) {
	
    $scope.reviewer = {};
    $scope.initReviewer = {};
    $scope.reviewers = [];
    $scope.selectedIndex;
    $scope.errors = {};
    
    
    // just to avoid entering author details repeatedly
    $scope.initAuthor.id=null;
    $scope.initAuthor.fname="sameer";
    $scope.initAuthor.lname="lname";
    $scope.initAuthor.email="email";
    $scope.initAuthor.mobile="9876543215";
    $scope.initAuthor.password="password";
    $scope.initAuthor.line1="line1";
    $scope.initAuthor.line2="line2";
    $scope.initAuthor.city="city";
    $scope.initAuthor.state="state";
    $scope.initAuthor.country="country";
    $scope.initAuthor.pincode="pincode";
    

    $scope.author= $scope.initReviewer;
    
    $scope.registerReviewer = function(){
    	
    	$scope.CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
    	console.log(angular.toJson($scope.reviewer));
    	console.log("CSRF_Token:"+ $scope.CSRF_TOKEN);
    	
    	var method = "POST";
    	
    	if($scope.reviewer.id>0){
    		method = "PUT";
    	}
    	
    	$http({
            url: '/reviewer/register',
            method: method,
            data: angular.toJson($scope.reviewer),
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': $scope.CSRF_TOKEN,
            }
            	 
        })
        .then(function(response) {
                // success
        	
        	console.log(response);
        	if(response.data.type=="success"){
        		//$scope.errors = {};
        	if(method=="POST"){
        	 $scope.reviewers.push(response.data.obj);   //insert
        	}else{
             $scope.reviewers[$scope.selectedIndex] = response.data.obj;  //update
        	}
        	
        	$scope.reviewer = $scope.initReviewer;
        	
        	  $('#accForm').find("input[type=text], textarea").val("");
        	
        	$('#registerModal').modal('toggle');
        	
        	}else {
        		
        		$scope.errors = response.data.errors;
        		console.log($scope.errors);
        		console.log(angular.equals($scope.errors, {}));
        	}
        	
        }, 
        function(response) { // optional
                // failed
        	console.log("failed");
        	console.log(response);
        });
    	
    }
    
$scope.getReviewers = function(){
    	
    	
    	
    	$http({
            url: '/reviewer/fetchAll',
            method: "GET",
            
            	 
        })
        .then(function(response) {
                // success
        	
        	console.log(response);
        	$scope.reviewers= response.data;
        	
        }, 
        function(response) { // optional
                // failed
        	console.log("failed");
        	console.log(response);
        });
    	
    }

$scope.getReviewers();



let copiedItem = null;

$scope.deleteReviewer = function(index){
	 
	$scope.CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
	var id =  $scope.reviewers[index].id;
	console.log("Deleting reviewer with ID:"+parseInt(id));
	
	 $http({
         url: '/reviewer/delete/'+parseInt(id),
         method: "DELETE",
         headers: {
             'X-CSRF-TOKEN': $scope.CSRF_TOKEN,
         }
         
         	 
     })
     .then(function(response) {
             // success
    	 
    	 $scope.authors.splice(index,1);
    	 
    	 
    	 
    	 console.log("Index is:"+index);
     	 console.log($scope.reviewers);
    	 console.log(response);
     	
     	
     }, 
     function(response) { // optional
             // failed
     	console.log("failed");
     	console.log(response);
     });
}


$scope.editReviewer = function(index){
	console.log("Index:"+index);
	copiedItem = Object.assign({}, copiedItem , $scope.reviewers[index] );
	$scope.reviewer = copiedItem;
	console.log($scope.reviewer);
	$scope.selectedIndex = index;

	$('#registerModal').modal('toggle');
	
 }

    
});