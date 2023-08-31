package com.projectManagement.projectManager.Service;

import com.projectManagement.projectManager.DAO.ProjectDAO;
import com.projectManagement.projectManager.DAO.UserDAO;
import com.projectManagement.projectManager.DTO.ProjectDTO;
import com.projectManagement.projectManager.Exceptions.IdResourceNotFoundException;
import com.projectManagement.projectManager.Models.Project;
import com.projectManagement.projectManager.Models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO,BigInteger userId) {
        User user = this.userDAO.findById(userId).orElseThrow(()->new IdResourceNotFoundException("User","Id",userId));
        Project project = this.DTOtoProject(projectDTO);
        project.setUser(user);
        Project addedProject = this.projectDAO.save(project);
        return this.ProjectToDTO(addedProject);
    }

    @Override
    public ProjectDTO updateProject(ProjectDTO projectDTO, BigInteger projectId) {
        Project project = this.projectDAO.findById(projectId).orElseThrow(()->new IdResourceNotFoundException("Project","Id",projectId));
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectNumber(projectDTO.getProjectNumber());
        project.setProjectTitle(projectDTO.getProjectTitle());
        project.setProjectContent(projectDTO.getProjectContent());
        Project updatedProject = this.projectDAO.save(project);
        return this.ProjectToDTO(updatedProject);
    }

    @Override
    public void deleteProject(BigInteger projectId) {
        Project project = this.projectDAO.findById(projectId).orElseThrow(()->new IdResourceNotFoundException("Project","Id",projectId));
        this.projectDAO.delete(project);

    }

    @Override
    public ProjectDTO getProject(BigInteger projectId) {
        Project project = this.projectDAO.findById(projectId).orElseThrow(()->new IdResourceNotFoundException("Project","Id",projectId));
        return this.ProjectToDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = this.projectDAO.findAll();
        List<ProjectDTO> projectDTOS = projects.stream().map(Project->ProjectToDTO(Project)).collect(Collectors.toList());
        return projectDTOS;
    }

    @Override
    public List<ProjectDTO> getProjectByUser(BigInteger userId) {
        User user = this.userDAO.findById(userId).orElseThrow(()->new IdResourceNotFoundException("User","Id",userId));
        List<Project> projects = this.projectDAO.findByUser(user);
        List<ProjectDTO> projectDTOS = projects.stream().map(Project->ProjectToDTO(Project)).collect(Collectors.toList());
        return projectDTOS;
    }

    @Override
    public List<ProjectDTO> searchProject(String keyword) {
        List<Project> result = this.projectDAO.findByProjectTitleContaining(keyword);
        List<ProjectDTO> resultDTO = result.stream().map(Project->ProjectToDTO(Project)).collect(Collectors.toList());
        return resultDTO;
    }

    private ProjectDTO ProjectToDTO(Project project)
    {
        return this.modelMapper.map(project,ProjectDTO.class);
    }

    private Project DTOtoProject(ProjectDTO projectDTO)
    {
        return this.modelMapper.map(projectDTO,Project.class);
    }
}
