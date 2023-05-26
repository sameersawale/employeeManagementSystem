package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.DTOs.EmployeeDto;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Employee employee=employeeRepository.findById(id).get();

        return employee;
    }

    @Override
    public String deleteEmployee(int id) {
        Employee employee=employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return employee.getFirstName()+" "+employee.getLastName()+" deleted successfully....";
    }

    @Override
    public Employee updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee=employeeRepository.findById(id).get();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setPosition(employeeDto.getPosition());
        employee.setEmail(employeeDto.getEmail());
        employee.setContactNumber(employeeDto.getContactNumber());

         employeeRepository.save(employee);
         return employee;
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList=employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public List<Employee> getByDepartment(String department) {
        List<Employee> employeeList=employeeRepository.findByDepartment(department);

        return employeeList;
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable= PageRequest.of(pageNo-1, pageSize, sort);
        return  employeeRepository.findAll(pageable);
    }


}
