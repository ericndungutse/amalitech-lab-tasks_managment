package com.ndungutse.project_tracker.service;

import com.ndungutse.project_tracker.model.Project;
import com.ndungutse.project_tracker.repository.ProjectRepository;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@DynamicUpdate
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
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

    public Project update(
            Long id,
            Project updatedProject
    ) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setDeadline(updatedProject.getDeadline());
            project.setStatus(updatedProject.isStatus());
            return projectRepository.save(project);
        }
        return null;
    }

    // Delete
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return projectRepository.existsById(id);
    }
}
