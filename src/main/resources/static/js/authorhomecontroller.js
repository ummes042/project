var app = angular.module('App', []);


 
app.controller('authorhomeCtrl', ['$scope', '$http', function ($scope, $http) {

    // List of user
    
	  
    $scope.paper = {};
    $scope.papers = [];
  
    $scope.CSRF_TOKEN = "";
    
    
   
    
    
    
   
   
   

    $scope.getPapers = function () {
        
        $http.get('/paper/fetch')
            .then(function success(e) {
            	console.log(e);
                   $scope.papers = e.data;
                
            }, function error(e) {

            });
    };
    $scope.getPapers();
    
    
  
    }]);
 

