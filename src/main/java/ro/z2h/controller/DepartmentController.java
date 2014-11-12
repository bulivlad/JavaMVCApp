package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by Buli on 11/11/2014.
 */

@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all")
    public ArrayList<Department> getAllDepartments(){
        ArrayList<Department> newList = new ArrayList<Department>();

        Department department1 = new Department();
        department1.setId(11L);
        department1.setDepartmentName("Department_1");
        newList.add(department1);

        Department department = new Department();
        department.setId(22L);
        department.setDepartmentName("Department_2");
        newList.add(department);
        return newList;
    }

}
