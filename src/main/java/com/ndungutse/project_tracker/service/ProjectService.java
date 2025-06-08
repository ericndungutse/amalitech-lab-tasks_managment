package com.ndungutse.project_tracker.service;

import com.ndungutse.project_tracker.dto.ProjectDTO;
import com.ndungutse.project_tracker.model.Project;
import com.ndungutse.project_tracker.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Create
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project project = projectDTO.toEntity();
        Project savedProject = projectRepository.save(project);
        return ProjectDTO.fromEntity(savedProject);
    }

    // Read
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Read with pagination
    public Page<ProjectDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projectPage = projectRepository.findAll(pageable);
        return projectPage.map(ProjectDTO::fromEntity);
    }

    public Optional<ProjectDTO> getById(Long id) {
        Optional<Project> projectOpt = projectRepository.findById(id);
        return projectOpt.map(ProjectDTO::fromEntity);
    }

    @Transactional
    public Optional<ProjectDTO> update(
            Long id,
            ProjectDTO updatedProjectDTO
    ) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();

            // Only update fields that are not null to leverage @DynamicUpdate
            if (updatedProjectDTO.getName() != null) {
                project.setName(updatedProjectDTO.getName());
            }

            if (updatedProjectDTO.getDescription() != null) {
                project.setDescription(updatedProjectDTO.getDescription());
            }

            if (updatedProjectDTO.getDeadline() != null) {
                project.setDeadline(updatedProjectDTO.getDeadline());
            }

            // Status is a primitive boolean, so we always update it
            project.setStatus(updatedProjectDTO.isStatus());

            return Optional.of(ProjectDTO.fromEntity(project));
        }
        return Optional.empty();
    }

    // Delete
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return projectRepository.existsById(id);
    }
}
