package ro.z2h.service;

import ro.z2h.domain.Employee;

import java.util.List;

/**
 * Created by Buli on 11/12/2014.
 */
public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findOneEmployee(String idEmployee);
    void deleteOneEmployee(String idEmployee);
    String addOneEmployee();

}
