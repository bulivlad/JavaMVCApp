package ro.z2h.service;

import ro.z2h.domain.Department;

import java.util.List;

/**
 * Created by Buli on 11/12/2014.
 */
public interface DepartmentService {

    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);

}
