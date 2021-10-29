package com.jjhernandezr.pma.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
  @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1, initialValue = 1)
  private Long projectId;

  private String name;
  private String stage;
  private String description;

  // @OneToMany(mappedBy = "project")

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))

  private List<Employee> employees;

  public Project() {

  }

  public Project(String name, String stage, String description) {
    this.name = name;
    this.stage = stage;
    this.description = description;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
