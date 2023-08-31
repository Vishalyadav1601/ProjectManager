package com.projectManagement.projectManager.Controller;

import com.projectManagement.projectManager.DTO.ApiResponse;
import com.projectManagement.projectManager.DTO.ProjectDTO;
import com.projectManagement.projectManager.Service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{userId}/project/add")
    public ResponseEntity<ProjectDTO> addProject (@Valid @RequestBody ProjectDTO projectDTO, @PathVariable BigInteger userId)
    {
        ProjectDTO addedProject = this.projectService.addProject(projectDTO,userId);
        return new ResponseEntity<>(addedProject, HttpStatus.CREATED);
    }

    @PutMapping("/project/{projectId}/update")
    public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody ProjectDTO projectDTO, @PathVariable BigInteger projectId)
    {
        ProjectDTO updatedProject = this.projectService.updateProject(projectDTO,projectId);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/project/{projectId}/delete")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable BigInteger projectId)
    {
        this.projectService.deleteProject(projectId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Project Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable BigInteger projectId)
    {
        ProjectDTO projectDTO = this.projectService.getProject(projectId);
        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<ProjectDTO>> getProjectByUser(@PathVariable BigInteger userId)
    {
        List<ProjectDTO> projectDTOS = this.projectService.getProjectByUser(userId);
        return ResponseEntity.ok(projectDTOS);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects ()
    {
        List<ProjectDTO> projectDTOS = this.projectService.getAllProjects();
        return ResponseEntity.ok(projectDTOS);
    }

    @GetMapping("/project/title/{keyword}")
    public ResponseEntity<List<ProjectDTO>> searchProject (@PathVariable String keyword)
    {
        List<ProjectDTO> result = this.projectService.searchProject(keyword);
        return ResponseEntity.ok(result);
    }
}
