/**
 * Created by Ben_Big on 3/15/16.
 */
(function(){
    angular
    .module("myapp",[])
    .controller("UserController",UserController);
}());


function UserController($scope, $http)
{
    $scope.user = {};

    $scope.createUser = function()
    {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/user',
            headers: {'Content-Type': 'application/json'},
            data: $scope.user
        }).success(function (data)
            {
                $scope.status=data;
            });
    };
}