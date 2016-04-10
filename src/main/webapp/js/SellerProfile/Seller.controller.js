/**
 * Created by Ben_Big on 4/4/16.
 */
(function(){
    angular.
        module("ShopApp").controller("sellerController",sellerController);
}())



function sellerController($scope,$http,UserService){
    $scope.currentUser=UserService.returnUser();

    //For test
    /*
    $scope.currentUser={id:24848,name:"Alice"};

    $scope.mySoldProducts=[
        {id:234,name:"car",shopperName:"Jerry",orderDate:"2008-11-11",shipStatus:true},
        {id:345,name:"motorcycle",shopperName:"George",orderDate:"2006-11-11",shipStatus:false},
        {id:456,name:"bicycle",shopperName:"Ellen",orderDate:"2007-11-13",shipStatus:true}
    ];*/




    $http.get("/mySoldProducts/"+$scope.currentUser.id)
        .then(function(res){
            $scope.mySoldProducts=res.data;
        });

    $scope.confirmShipping=function(product){
        console.log(product.id);
        product.shipStatus=true;
        $http.put("/confirmShipping/"+product.id)
            .then(function(res){
                product.shipStatus=true;
            })
    }



}