package com.jjhernandezr.pma.dao;

import com.jjhernandezr.pma.dto.ChartData;
import com.jjhernandezr.pma.entities.Project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

  @Override
  public List<Project> findAll();

  @Query(nativeQuery = true, value =
      "SELECT stage, COUNT(name) as projectsCount " +
          "FROM project " +
          "GROUP BY stage ORDER BY 2 DESC")

  public List<ChartData> projectStageCount();
}
