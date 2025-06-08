package com.ndungutse.project_tracker.controller;

import com.ndungutse.project_tracker.dto.PageResponse;
import com.ndungutse.project_tracker.dto.ProjectDTO;
import com.ndungutse.project_tracker.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.create(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Get all projects with pagination
    @GetMapping
    public ResponseEntity<PageResponse<ProjectDTO>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        int pageToGet = page == 0 ? page : page - 1;
        Page<ProjectDTO> projects = projectService.getAll(pageToGet, size);
        PageResponse<ProjectDTO> response = new PageResponse<>(projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Optional<ProjectDTO> project = projectService.getById(id);
        return project.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a project
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectDTO projectDTO
    ) {
        if (projectService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProjectDTO updatedProject = projectService.update(id, projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (!projectService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
