/**
 * Created by Ben_Big on 3/26/16.
 */
(function(){
    angular.
        module("ShopApp").controller("productController",productController);
}());

function productController($scope,$location,$http,UserService) {
    $scope.productID = $location.search().productID;

    $http.get("/singleproduct?productID=" + $scope.productID)
        .then(
            function (res) {
                $scope.currentProduct = res.data;
                //console.log($scope.currentProduct);

                $http.get("/shop/info?shopOwner=" + $scope.currentProduct.owner)
                    .then(function (res) {
                        $scope.info = res.data;
                        $scope.categories = $scope.info.Categories;
                        //console.log($scope.categories);
                        console.log(UserService.returnUser());
                    })


            });
}