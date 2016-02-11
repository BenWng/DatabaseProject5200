/**
 * Created by Ben_Big on 2/11/16.
 */

(function(){
    angular
        .module("PageApp")
        .factory("UserService",UserService);

    function UserService($rootScope,$q,$http){

        var service = {
            createUser : createUser,
            findUsersAll: findUsersAll,
            findUserById: findUserById,
            updateUserById: updateUserById,
            deleteUserById: deleteUserById
        };

        return service;

        function createUser(newUser){

            return $http.post("/api/project/user",newUser);
        }

        function findUsersAll(){
            //console.log("findUsersAll");
            return $http.get("/api/project/usersall");
        }


        function findUserById(id){

            return $http.get("/api/project/user/"+id);
        }




        function updateUserById(id,user){

            return $http.put("/api/project/user/"+id,user);
        }


        function deleteUserById(id){
            return $http.delete("/api/project/user/"+id);
        }



    }
})();
