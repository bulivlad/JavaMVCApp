package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Buli on 11/12/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {
        EmployeeDao getEmployeeFromDao = new EmployeeDao();

        ArrayList<Employee> employeeArrayList = null;
        DatabaseConnection newDatabaseConnection = DatabaseConnection.getInstance();
        Connection con = newDatabaseConnection.getConnection();
        employeeArrayList = getEmployeeFromDao.getAllEmployees(con);
        return employeeArrayList;
    }

    @Override
    public Employee findOneEmployee(Long id) {
        EmployeeDao getEmployeeFromDao = new EmployeeDao();

        Employee employeeFromDB = new Employee();
        DatabaseConnection newDataBaseConnection = DatabaseConnection.getInstance();
        Connection con = newDataBaseConnection.getConnection();
        employeeFromDB = getEmployeeFromDao.getEmployeeById(con, id);
        return null;
    }
}
