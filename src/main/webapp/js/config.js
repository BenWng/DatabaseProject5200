/**
 * Created by Ben_Big on 3/24/16.
 */
(function(){
    angular
        .module("ShopApp")
        .config(Configuration)

    function Configuration($routeProvider){
        $routeProvider
            .when('/app/home',{
                templateUrl:'./home.html'
            })
            .when('/app/shop',{
                templateUrl:'./shop.html'
            })
            .when('/app/product',{
                templateUrl:'./product.html'
            })
            .when('/app/login',{
                templateUrl:'./login.html'
            })
            .when('/app/registration',{
                templateUrl:'./registration.html'
            })
            .when('/app/administrator',{
                templateUrl:'./Administrator.html'
            })
            .when('/app/shopperprofile',{
                templateUrl:'./ShopperProfile.html'
            })
            .when('/app/sellerprofile',{
                templateUrl:'./SellerProfile.html'
            })
            .when('/app/editor',{
                templateUrl:'./Editor.html'
            })
            .otherwise({
                redirectTo:'/app/home'
            });

    }
}());