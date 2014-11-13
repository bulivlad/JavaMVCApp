package ro.z2h.service;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
        try {
            employeeArrayList = getEmployeeFromDao.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeArrayList;
    }

    @Override
    public Employee findOneEmployee(String idEmployee) {
        EmployeeDao getEmployeeFromDao = new EmployeeDao();

        Employee employeeFromDB = new Employee();
        DatabaseConnection newDataBaseConnection = DatabaseConnection.getInstance();
        Connection con = newDataBaseConnection.getConnection();
        try {
            employeeFromDB = getEmployeeFromDao.getEmployeeById(con, Long.parseLong(idEmployee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeFromDB;
    }

    @Override
    public void deleteOneEmployee(String idEmployee) {
        EmployeeDao getEmployeeFromDao = new EmployeeDao();
        Employee employee = new Employee();
        DatabaseConnection newDatabaseConnection = DatabaseConnection.getInstance();
        Connection con = newDatabaseConnection.getConnection();

        try {
           employee = getEmployeeFromDao.getEmployeeById(con,Long.parseLong(idEmployee));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getEmployeeFromDao.deleteEmployee(employee,con);
    }

    @Override
    public String addOneEmployee() {

        ObjectMapper newJson = new ObjectMapper();
        String valueAsString = null;

//        try {
//            valueAsString = newJson.writeValueAsString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return valueAsString;
    }
}
