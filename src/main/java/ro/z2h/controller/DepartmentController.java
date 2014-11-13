package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;
import ro.z2h.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Buli on 11/11/2014.
 */

@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments(){
        List<Department> newList = new ArrayList<Department>();

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        newList = departmentService.getAllDepartments();
        return newList;
    }

    @MyRequestMethod(urlPath = "/one")
    public Department getDepartmentById(String idDepartment){
        Department departmentFromDB = new Department();

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        departmentFromDB = departmentService.getDepartmentById(idDepartment);

        return departmentFromDB;
    }

}
