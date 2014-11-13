hrApp.controller('EmployeeListController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.employees = [];
    $http({url: 'http://localhost:8080/app/mvc/employee/all', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.employees = data;
        });
    $scope.viewEmployee = function(employeeid) {
        $location.url('/employeeview/'+employeeid);
    };
    $scope.editEmployee = function(employeeid) {
        $location.url('/employeeedit/'+employeeid);
    };
    $scope.deleteEmployee = function(employeeid) {
        $location.url('/employeedelete/'+employeeid);
    };
}]);