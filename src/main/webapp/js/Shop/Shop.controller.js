/**
 * Created by Ben_Big on 3/24/16.
 */
(function(){
    angular.module("ShopApp").controller("shopController",shopController);
}());



function shopController($location,$http,$scope,$rootScope,UserService){

    $scope.shopOwner=$location.search().shopOwner;
    //console.log($scope.shopOwner);


    $scope.currentCategory=$location.search().category;
    //console.log(currentCategory);


    //test data
    /*
    $scope.products=[{name:"First Product",price:24.99,description:"some description"}
        ,{name:"Second Product",price:64.99,description:"some more description"}];
    */

    if ($scope.currentCategory===undefined) {
        $http.get("/shop/products?shopOwner=" + $scope.shopOwner)
            .then(function (res) {
                $scope.products = res.data;
                //console.log(res.data);
            });
    }
    else{
        $http.get("/shop/products?shopOwner=" + $scope.shopOwner+"&category=" + $scope.currentCategory)
            .then(function (res) {
                $scope.products = res.data;
                //console.log(res.data);
            });
    }


    $http.get("/shop/info?shopOwner="+$scope.shopOwner)
        .then(function(res){
            $scope.info=res.data;
            $scope.categories=$scope.info.Categories;
            //console.log($scope.categories);
        })




}


