package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public Employee getEmployee(int id);

    public String deleteEmployee(int id);

//    public Employee updateEmployee(Employee employee);

    public List<Employee> getByDepartment(String department);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
