package com.projectManagement.projectManager.DAO;

import com.projectManagement.projectManager.DTO.ProjectDTO;import com.projectManagement.projectManager.Models.Project;
import com.projectManagement.projectManager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProjectDAO extends JpaRepository<Project, BigInteger> {
    List<Project> findByUser(User user);
    List<Project> findByProjectTitleContaining(String projectTitle);
}
