package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.fileUpload.FileUploadUtil;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/newEmployee")
    public String newEmployee(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/add")
    public RedirectView addEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        employee.setPicture(fileName);

        Employee employee1=employeeService.addEmployee(employee);
        String uploadDir= "./picture/"+employee1.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/", true);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {

        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/employeeSearch")
    public String getByDepartment(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeSearch";
    }

    @PostMapping("/employeeSearch")
    public String employeeSearch(Employee employee, Model model, String department) {
        List<Employee> employeeList = employeeService.getByDepartment(department);
        model.addAttribute("employeeList", employeeList);
        return "employeeSearch";
    }


    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> employeeList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalElements());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("employeeList", employeeList);

        return "index";
    }
}
