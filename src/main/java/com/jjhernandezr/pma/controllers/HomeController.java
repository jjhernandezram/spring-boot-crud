package com.jjhernandezr.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jjhernandezr.pma.dao.EmployeeRepository;
import com.jjhernandezr.pma.dao.ProjectRepository;
import com.jjhernandezr.pma.dto.ChartData;
import com.jjhernandezr.pma.dto.EmployeeProject;
import com.jjhernandezr.pma.entities.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

  // Leyendo datos desde "application.properties".
  @Value("${version}")
  private String ver;

  @Autowired
  ProjectRepository proRepo;

  @Autowired
  EmployeeRepository empRepo;

  @GetMapping("/")
  public String displayHome(Model model) throws JsonProcessingException {

    model.addAttribute("versionNumber", ver);

    // Fetching from database info.
    List<ChartData> projectData = proRepo.projectStageCount();

    // Parsing projectData from database into a Json structure for JS data manipulation.
    ObjectMapper objectMapper = new ObjectMapper();
    String objectString = objectMapper.writeValueAsString(projectData);
    model.addAttribute("projectStatusCount", objectString);

    List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
    model.addAttribute("employeesListProjectsCount", employeesProjectCount);

    List<Project> projects = proRepo.findAll();
    model.addAttribute("projects", projects);
    return "main/home";
  }
}
