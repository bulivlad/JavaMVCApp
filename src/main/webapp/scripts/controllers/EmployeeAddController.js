hrApp.controller('EmployeeAddController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.employee = {};

    $http({url: 'http://localhost:8080/app/mvc/department/all', method: 'GET'}).
        success(function (data) {
            $scope.departments = data;
        });
    $http({url: 'http://localhost:8080/app/mvc/employee/all', method: 'GET'}).
        success(function (data) {
            $scope.managers = data;
        });

    $http({url: 'http://localhost:8080/app/mvc/job/all', method: 'GET'}).
        success(function (data) {
            $scope.jobs = data;
        });

    /**
     * Reset form
     */
    $scope.reset = function () {
        this.employee = {};
    };

    /**
     * Persist an employee
     * @param addEmployee - employee to be persisted
     */
    $scope.create = function (addEmployee) {
        $http({url: 'http://localhost:8080/app/mvc/employee/create', method: 'PUT',data:addEmployee}).
            success(function (data) {
                $scope.employee = data;
                $location.url('/employeeview/'+$scope.employee.employeeId);
            });
    }
}]);