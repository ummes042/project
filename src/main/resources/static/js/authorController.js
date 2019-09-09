




var app = angular.module('myApp', []);
app.controller('authorCtrl', function($scope,$http) {
	
    $scope.author = {};
    $scope.initAuthor = {};
    $scope.authors = [];
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
    

    $scope.author= $scope.initAuthor;
    
    $scope.registerAuthor = function(){
    	$scope.CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
    	console.log(angular.toJson($scope.author));
    	console.log("CSRF_Token:"+ $scope.CSRF_TOKEN);
    	var method = "POST";
    	
    	if($scope.author.id>0){
    		method = "PUT";
    	}
    	
    	$http({
            url: '/author/register',
            method: method,
            data: angular.toJson($scope.author),
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': $scope.CSRF_TOKEN,
            }
            	 
        })
        .then(function(response) {
                // success
        	
        	console.log(response);
        	if(response.data.type=="success"){
        		$scope.errors = {};
        	if(method=="POST"){
        	 $scope.authors.push(response.data.obj);   //insert
        	}else{
             $scope.authors[$scope.selectedIndex] = response.data.obj;  //update
        	}
        	
        	$scope.author = $scope.initAuthor;
        	
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
    
$scope.getAuthors = function(){
    	
    	
    	
    	$http({
            url: '/author/fetchAll',
            method: "GET",
            
            	 
        })
        .then(function(response) {
                // success
        	
        	console.log(response);
        	$scope.authors= response.data;
        	
        }, 
        function(response) { // optional
                // failed
        	console.log("failed");
        	console.log(response);
        });
    	
    }

$scope.getAuthors();



let copiedItem = null;

$scope.deleteAuthor = function(index){
	 
	$scope.CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
	var id =  $scope.authors[index].id;
	console.log("Deleting Author with ID:"+parseInt(id));
	
	 $http({
         url: '/author/delete/'+parseInt(id),
         method: "DELETE",
         headers: {
             'X-CSRF-TOKEN': $scope.CSRF_TOKEN,
         }
         
         	 
     })
     .then(function(response) {
             // success
    	 
    	 $scope.authors.splice(index,1);
    	 
    	 
    	 
    	 console.log("Index is:"+index);
     	 console.log($scope.authors);
    	 console.log(response);
     	
     	
     }, 
     function(response) { // optional
             // failed
     	console.log("failed");
     	console.log(response);
     });
}


$scope.editAuthor = function(index){
	console.log("Index:"+index);
	copiedItem = Object.assign({}, copiedItem , $scope.authors[index] );
	$scope.author = copiedItem;
	console.log($scope.author);
	$scope.selectedIndex = index;

	$('#registerModal').modal('toggle');
	
 }

    
});