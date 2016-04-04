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
            .otherwise({
                redirectTo:'/app/home'
            });

    }
}());