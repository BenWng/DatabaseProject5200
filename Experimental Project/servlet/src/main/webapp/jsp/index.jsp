<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Main Page</title>


    <!-- Bootstrap Core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- Angular JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.min.js"></script>


    <!-- AngularJS app -->
    <script src="./app.js"></script>

    <!-- Services -->
    <script src="./PageService.js"></script>

    <!-- Page Controllers -->
    <script src="./Page.controller.js"></script>


</head>

<body>


<div id="wrapper" ng-controller="pageController">

    <!-- Page Content -->
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Experiment Page
                </h1>
            </div>
        </div>
        <!-- /.row -->



        <!--The Registered Students Tab -->
        <div  id="registered">
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        <!-- The Table of the registered students-->
                        <table  class="table table-bordred table-striped">


                            <thead>
                            <th class="col-xs-2">Name</th>
                            <th class="col-xs-2">Email Account</th>
                            <th class="col-xs-2">Role</th>
                            <th class="col-xs-2">Edit</th>
                            <th class="col-xs-2">Delete</th>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input class="form-control" ng-model="user.Name"></td>
                                <td><input class="form-control" ng-model="user.account"></td>
                                <td><input class="form-control" ng-model="user.role"></td>
                                <td> <button class="btn btn-success" ng-click="updateUserById(user.id,user)">Update</button></td>
                                <td></td>
                            </tr>
                            <tr ng-repeat="user in users">
                                <td>{{user.Name}}</td>
                                <td>{{user.account}}</td>
                                <td>{{user.role}}</td>
                           

                                <td><button class="btn btn-primary btn-xs" data-title="Edit" ng-click="findUserById(user.id)"><span class="glyphicon glyphicon-pencil" ></span></button></td>
                                <td><button class="btn btn-danger btn-xs" data-title="Delete" ng-click="deleteUserById(user.id)"><span class="glyphicon glyphicon-trash" ></span></button></p></td>

                            </tr>

                            </tbody>

                        </table>
                        <!-- The end of the table -->



                        <div class="clearfix"></div>



                    </div>

                </div>
            </div>
        </div>
        <!-- End of the Registered Students Tab-->


    </div>


    <!-- /.container -->


</div>


</body>


</html>