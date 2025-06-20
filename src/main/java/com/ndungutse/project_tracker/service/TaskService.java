package com.ndungutse.project_tracker.service;

import com.ndungutse.project_tracker.dto.DeveloperDTO;
import com.ndungutse.project_tracker.dto.ProjectDTO;
import com.ndungutse.project_tracker.dto.TaskDTO;
import com.ndungutse.project_tracker.model.Developer;
import com.ndungutse.project_tracker.model.Project;
import com.ndungutse.project_tracker.model.Task;
import com.ndungutse.project_tracker.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final DeveloperService developerService;

    public TaskService(
            TaskRepository taskRepository,
            ProjectService projectService,
            DeveloperService developerService
    ) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.developerService = developerService;
    }

    // Create
    @Transactional
    public Optional<TaskDTO> create(TaskDTO taskDTO) {
        // Validate that the project exists
        if (taskDTO.getProjectId() == null || !projectService.exists(taskDTO.getProjectId())) {
            return Optional.empty();
        }

        // Get the project entity
        Optional<Project> project = projectService.getById(taskDTO.getProjectId())
                .map(ProjectDTO::toEntity);

        if (project.isEmpty()) {
            return Optional.empty();
        }

        // Get the developer entity if provided
        Developer developer = null;
        if (taskDTO.getDeveloperId() != null && developerService.exists(taskDTO.getDeveloperId())) {
            developer = developerService.getById(taskDTO.getDeveloperId())
                    .map(DeveloperDTO::toEntity)
                    .orElse(null);
        }

        // Create and save the task
        Task task = taskDTO.toEntity(project.get(), developer);
        Task savedTask = taskRepository.save(task);

        return Optional.of(TaskDTO.fromEntity(savedTask));
    }

    // Read
    public List<TaskDTO> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map((task) -> {
                    return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.isStatus(), task.getDueDate(), task.getDeveloperId(), task.getProject().getId());
                }).collect(Collectors.toList());
    }

    public Optional<TaskDTO> getById(Long id) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        return taskOpt.map(TaskDTO::fromEntity);
    }

    // Update
    @Transactional
    public Optional<TaskDTO> update(
            Long id,
            TaskDTO updatedTaskDTO
    ) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);

        if (existingTaskOpt.isEmpty()) {
            return Optional.empty();
        }

        Task existingTask = existingTaskOpt.get();

        // Update fields that are not null
        if (updatedTaskDTO.getTitle() != null) {
            existingTask.setTitle(updatedTaskDTO.getTitle());
        }

        if (updatedTaskDTO.getDescription() != null) {
            existingTask.setDescription(updatedTaskDTO.getDescription());
        }

        if (updatedTaskDTO.getDueDate() != null) {
            existingTask.setDueDate(updatedTaskDTO.getDueDate());
        }

        // Status is a primitive boolean, so we always update it
        existingTask.setStatus(updatedTaskDTO.isStatus());

        // Update project if provided and exists
        if (updatedTaskDTO.getProjectId() != null &&
                projectService.exists(updatedTaskDTO.getProjectId())) {

            Optional<Project> projectOpt = projectService.getById(updatedTaskDTO.getProjectId())
                    .map(ProjectDTO::toEntity);

            projectOpt.ifPresent(existingTask::setProject);
        }

        // Update developer if provided and exists
        if (updatedTaskDTO.getDeveloperId() != null &&
                developerService.exists(updatedTaskDTO.getDeveloperId())) {

            Optional<Developer> developerOpt = developerService.getById(updatedTaskDTO.getDeveloperId())
                    .map(DeveloperDTO::toEntity);

            developerOpt.ifPresent(existingTask::setDeveloper);
        }

        Task updatedTask = taskRepository.save(existingTask);
        return Optional.of(TaskDTO.fromEntity(updatedTask));
    }

    // Delete
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return taskRepository.existsById(id);
    }
}
