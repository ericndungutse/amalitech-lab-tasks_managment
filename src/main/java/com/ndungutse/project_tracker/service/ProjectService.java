package com.ndungutse.project_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ndungutse.project_tracker.model.Project;
import com.ndungutse.project_tracker.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Create
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    // Read
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Optional<Project> update(
            Long id,
            Project updatedProject) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();

            // Only update fields that are not null to leverage @DynamicUpdate
            if (updatedProject.getName() != null) {
                project.setName(updatedProject.getName());
            }

            if (updatedProject.getDescription() != null) {
                project.setDescription(updatedProject.getDescription());
            }

            if (updatedProject.getDeadline() != null) {
                project.setDeadline(updatedProject.getDeadline());
            }

            // Status is a primitive boolean, so we always update it
            project.setStatus(updatedProject.isStatus());

        }
        return existingProject;
    }

    // Delete
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return projectRepository.existsById(id);
    }
}
