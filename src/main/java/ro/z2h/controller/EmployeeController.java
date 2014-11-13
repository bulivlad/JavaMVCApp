package ro.z2h.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeService;
import ro.z2h.service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Buli on 11/11/2014.
 */
@MyController(urlPath = "/employee")
public class EmployeeController{

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees(){
        List<Employee> newList = new LinkedList<Employee>();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//
//        Employee employee1 = new Employee();
//        employee1.setId(11L);
//        employee1.setFirstName("Bobby");
//
//        newList.add(employee1);
//
//        Employee employee = new Employee();
//        employee.setId(22L);
//        employee.setFirstName("Fobby");
//        newList.add(employee);

        newList = employeeService.findAllEmployees();

        return newList;
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(String idEmployee){
        EmployeeServiceImpl oneEmployee = new EmployeeServiceImpl();

        Employee employee = new Employee();
        employee = oneEmployee.findOneEmployee(idEmployee);

        return employee;
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public void deleteOneEmployee(String idEmployee){
        EmployeeServiceImpl deleteEmployee = new EmployeeServiceImpl();

        deleteEmployee.deleteOneEmployee(idEmployee);
    }

    @MyRequestMethod(urlPath = "/create", methodType = "PUT")
    public String addOneEmployee(){
        EmployeeServiceImpl createNewEmployee = new EmployeeServiceImpl();

        return createNewEmployee.addOneEmployee();
    }

}
