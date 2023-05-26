package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.DTOs.EmployeeDto;
import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public Employee getEmployee(int id);

    public String deleteEmployee(int id);

    public Employee updateEmployee(int id, EmployeeDto employeeDto);

    public List<Employee> getEmployeeList();

    public List<Employee> getByDepartment(String department);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
