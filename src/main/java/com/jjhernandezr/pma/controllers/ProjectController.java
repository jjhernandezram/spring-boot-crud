package com.jjhernandezr.pma.controllers;

import com.jjhernandezr.pma.dao.EmployeeRepository;
import com.jjhernandezr.pma.dao.ProjectRepository;
import com.jjhernandezr.pma.entities.Employee;
import com.jjhernandezr.pma.entities.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

// Ruta padre
@RequestMapping("/projects")

public class ProjectController {

  @Autowired
  ProjectRepository proRepo;

  @Autowired
  EmployeeRepository empRepo;

  // Ruta hija
  @GetMapping("/new")
  public String newProject(Model model) {
    Project aProject = new Project();
    model.addAttribute("project", aProject);

    List<Employee> employees = empRepo.findAll();
    model.addAttribute("allEmployees", employees);

    // Returna la vista ubicada en la carpeta resources/template/"nombre-de-la-vista".html
    return "projects/new-project";
  }

  @GetMapping("/list")
  public String listProjects(Model model) {
    List<Project> projects = proRepo.findAll();
    model.addAttribute("projects", projects);

    // Returna la vista ubicada en la carpeta resources/template/"nombre-de-la-vista".html
    return "projects/list-projects";
  }

  @PostMapping(value = "/save")
  public String createProject(Project project, Model model) {
    proRepo.save(project);

    // Redireccionado para evitar multiples submits.
    return "redirect:/projects/list";

  }
}
