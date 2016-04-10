(function(){
    angular
        .module("ShopApp").controller("editorController",editorController);
}());


function editorController($scope,$http,$routeParams,$location){

    var productID=$routeParams.productID;

    //console.log(productID);

    $http.get("/singleproduct?productID=" + productID)
        .then(function(res){
            $scope.product=res.data;
        })


    //For test
    /*
    $scope.product={
        id:1234,
        name: "something",
        description: " short description",
        longdescription: "long description",
        price: 18,
        owner: 789, //the id of the owner
        Category:"Food" ,//the category of the product
        orderDate:"2007-11-13",
        shipStatus:true
    }*/

    $scope.submitPost=function(){

        //$location.path("/app/product").search('productID',$scope.product.id);

        $http.put("/editPost/"+$scope.product.id,$scope.product)
            .then(function(res){
                $location.path("/app/product").search('productID',$scope.product.id);
            })
    }


    $scope.deletePost=function(){

        //$location.path("/app/home");

        $http.delete("/deletePost/"+$scope.product.id)
            .then(function(res){
                $location.path("/app/home");
            })


    }


}
