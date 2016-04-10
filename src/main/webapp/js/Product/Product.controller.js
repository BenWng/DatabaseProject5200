/**
 * Created by Ben_Big on 3/26/16.
 */
(function(){
    angular.
        module("ShopApp").controller("productController",productController);
}());

function productController($scope,$location,$http,UserService) {
    $scope.productID = $location.search().productID;
    $scope.purchasedFlag=false;
    $scope.wishFlag=false;
    $scope.editFlag=false;

    $http.get("/singleproduct?productID=" + $scope.productID)
        .then(
            function (res) {
                $scope.currentProduct = res.data;
                //console.log($scope.currentProduct.owner);
                //console.log(UserService.returnUser().id);
                //console.log($scope.currentProduct);
                if (UserService.returnUser()!=null
                    && $scope.currentProduct.owner == UserService.returnUser().id){
                    $scope.editFlag=true;
                }
                $http.get("/shop/info?shopOwner=" + $scope.currentProduct.owner)
                    .then(function (res) {
                        $scope.info = res.data;
                        $scope.categories = $scope.info.Categories;
                        //console.log($scope.categories);
                        console.log(UserService.returnUser());
                    })


            });

    if (UserService.returnUser()!=null) {
        $http.get("/purchasedOrNot/" + $scope.productID + "/" + UserService.returnUser().id)
            .then(function (res) {
                $scope.purchasedFlag = res.data.purchasedFlag;
            })
    }

    if (UserService.returnUser()!=null) {
        $http.get("/wishOrNot/" + $scope.productID + "/" + UserService.returnUser().id)
            .then(function (res) {
                $scope.wishFlag = res.data.wishFlag;
            })
    }


    $scope.purchase=function(id) {
        if (UserService.returnUser() == null) {
            window.alert("Log in first");
        }
        else {

            $http.put("/purchaseProduct/" + $scope.productID + "/" +UserService.returnUser().id)
                .then(
                    function (res) {
                        $scope.purchasedFlag = true;
                    }
                )
        }
    }

    $scope.addToWishList=function(id){
        if (UserService.returnUser() == null) {
            window.alert("Log in first");
        }
        else {
            $http.put("/addToWishList/" + $scope.productID+ "/" +UserService.returnUser().id)
                .then(
                    function (res) {
                        $scope.wishFlag = true;
                    }
                )
        }
    }



}