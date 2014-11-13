package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.domain.Department;
import ro.z2h.utils.DatabaseConnection;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Buli on 11/12/2014.
 */
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<Department> getAllDepartments() {
        DepartmentDao newDepartmentDao = new DepartmentDao();
        List<Department> newDepartmentsList = new ArrayList<Department>();

        DatabaseConnection DBConnection = DatabaseConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            newDepartmentsList = newDepartmentDao.getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newDepartmentsList;
    }

    @Override
    public Department getDepartmentById(String idDepartment) {
        DepartmentDao newDepartmentDao = new DepartmentDao();
        Department departmentFromDB = new Department();

        DatabaseConnection DBConnection = DatabaseConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            departmentFromDB = newDepartmentDao.getDepartmentById(con,Long.parseLong(idDepartment));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentFromDB;
    }
}
