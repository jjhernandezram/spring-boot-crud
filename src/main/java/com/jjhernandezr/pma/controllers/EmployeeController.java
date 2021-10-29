package com.jjhernandezr.pma.controllers;
import com.jjhernandezr.pma.dao.EmployeeRepository;
import com.jjhernandezr.pma.entities.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping("/employees")

public class EmployeeController {

  @Autowired
  EmployeeRepository empRepo;

  @GetMapping("/new")
  public String newEmployee(Model model) {
    Employee aEmployee = new Employee();
    model.addAttribute("employee", aEmployee);
    return "employees/new-employee";
  }

  @GetMapping("/list")
  public String listEmployees(Model model) {
    List<Employee> employees = empRepo.findAll();
    model.addAttribute("employees", employees);
    return "employees/list-employees";
  }

  @PostMapping("/save")
  public String newEmployee(Employee employee, Model model) {
    empRepo.save(employee);
    return "redirect:/employees/list";
  }
}
