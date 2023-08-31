package com.projectManagement.projectManager.Service;

import com.projectManagement.projectManager.DTO.ProjectDTO;

import java.math.BigInteger;
import java.util.List;

public interface ProjectService {
    ProjectDTO addProject (ProjectDTO projectDTO,BigInteger userId);
    ProjectDTO updateProject (ProjectDTO projectDTO, BigInteger projectId);
    void deleteProject(BigInteger projectId);
    ProjectDTO getProject(BigInteger projectId);
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getProjectByUser(BigInteger userId);
    List<ProjectDTO> searchProject(String keyword);
}
