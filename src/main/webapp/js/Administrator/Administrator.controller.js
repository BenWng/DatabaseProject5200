(function(){
    angular
        .module("ShopApp").controller("AdminController",AdminController);
}());


function AdminController($http,$scope){

    //For Test
    /*
    var usersForTest=[
        {id:234,name:"Tom",email:"Tom@gmail.com"},
        {id:345,name:"Mary",email:"Mary@gmail.com"},
        {id:456,name:"Alice",email:"Alice@gmail.com"}
    ];
    $scope.users=usersForTest;*/


    $http.get("/users")
        .then(function(res){
            $scope.users=res.data;
        });


    $scope.selectUser=function(user){

        $scope.userToEdit={
            id:user.id,
            name:user.name,
            email:user.email
        };
    }


    $scope.deleteUserById=function(id) {
        var r = confirm("'Are you sure you want to delete this user?");
        if (r == true) {
            console.log(id);
            $http.delete("deleteuser/"+id)
                .then(function(res){
                    $scope.users=res.data;
                })
        }
    }

    $scope.updateUserById=function(id,user){
        console.log(id);
        console.log(user);
        $http.put("/updateuser/"+id,user)
            .then(function(res){
                $scope.users=res.data;
            })
    }
}