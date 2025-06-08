package com.ndungutse.project_tracker.service;

import com.ndungutse.project_tracker.model.Project;
import com.ndungutse.project_tracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public Project create(Project project) {
        return projectRepository.save(project);
    }
}
