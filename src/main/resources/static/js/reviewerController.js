var app = angular.module('myApp', []);


 
app.controller('reviewerCtrl', ['$scope', '$http', function ($scope, $http) {

    // List of user
    
	  
    $scope.paper = {};
    $scope.papers = [];
  
    $scope.CSRF_TOKEN = "";
    
    $scope.getPapers = function () {
        
        $http.get('/reviewer/rest/fetch/paper')
            .then(function success(e) {
            	console.log(e);
                   $scope.papers = e.data;
                
            }, function error(e) {

            });
    };
    $scope.getPapers();
    
 $scope.accept = function (index) {
	 paper = $scope.papers[index];
        $http.get('/paper/accept/'+paper.id)
            .then(function success(e) {
            	console.log(e);
                   $scope.papers[index].status = e.data.status;
                
            }, function error(e) {

            });
    };
    
    
  
    }]);
 

