(function(){
    angular
        .module("ShopApp").controller("shopperController",shopperController)
}());


function shopperController($scope,$http,UserService){
    $scope.currentUser=UserService.returnUser();



    //For test
    /*
    $scope.currentUser={id:24848,name:"Alice"};

    $scope.myPurchases=[
        {id:234,name:"car",description:"A nice car",orderDate:"2008-11-11",shipStatus:true},
        {id:345,name:"motorcycle",description:"A nice motorcycle",orderDate:"2006-11-11",shipStatus:false},
        {id:456,name:"bicycle",description:"A nice bicycle",orderDate:"2007-11-13",shipStatus:true}
    ];


    $scope.myWishList=[
        {id:234,name:"Tesla",description:"Electric Car"},
        {id:345,name:"Ferrari",description:"Sport Car"},
        {id:456,name:"Acura",description:"Good Car"}
    ];*/


    $http.get("/myPurchases/"+$scope.currentUser.id)
        .then(function(res){
            $scope.myPurchases=res.data;
        });

    $http.get("/myWishList/"+$scope.currentUser.id)
        .then(function(res){
            $scope.myWishList=res.data;
        });

    $scope.deleteFromWishList=function(userId,productId){
        console.log(userId);
        console.log(productId);
        $http.delete("/removeFromWishList/"+userId+"/"+productId)
            .then(function(res){
            $scope.myWishList=res.data;
        })
    }

}