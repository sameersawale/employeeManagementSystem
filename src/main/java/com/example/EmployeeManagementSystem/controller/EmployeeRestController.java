package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.DTOs.EmployeeDto;
import com.example.EmployeeManagementSystem.fileUpload.FileUploadUtil;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    EmployeeServiceImpl employeeService;

//    @PostMapping(value = "/add",
//           produces = {MediaType.APPLICATION_JSON_VALUE} )
//    public String addFile(@RequestPart("file") MultipartFile multipartFile)throws IOException{
//
//        String filename=multipartFile.getOriginalFilename();
//        String uploadDir= "./picture/";
//        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
//        return "file uploaded";
//    }

    @PostMapping(value = "/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE} )
    public String addEmployee( @RequestParam("file") MultipartFile multipartFile, @RequestPart("employee") Employee employee ) throws IOException {

        String fileName= multipartFile.getOriginalFilename();

        employee.setPicture(fileName);

        Employee employee1= employeeService.addEmployee(employee);

        String uploadDir= "./picture/"+employee1.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "employee added successfully......";
    }

    @GetMapping("/list")
    public List<Employee> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable("id") int id){
        return employeeService.getEmployee(id);
    }

    @PutMapping("/edit/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable("department") String department){
        return employeeService.getByDepartment(department);
    }


}
