package com.ndungutse.project_tracker.service;

import com.ndungutse.project_tracker.dto.DeveloperDTO;
import com.ndungutse.project_tracker.model.Developer;
import com.ndungutse.project_tracker.model.Task;
import com.ndungutse.project_tracker.repository.DeveloperRepository;
import com.ndungutse.project_tracker.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final TaskRepository taskRepository;

    public DeveloperService(
            DeveloperRepository developerRepository,
            TaskRepository taskRepository
    ) {
        this.developerRepository = developerRepository;
        this.taskRepository = taskRepository;
    }

    // Create
    public DeveloperDTO create(DeveloperDTO developerDTO) {
        Developer developer = developerDTO.toEntity();
        Developer savedDeveloper = developerRepository.save(developer);
        return DeveloperDTO.fromEntity(savedDeveloper);
    }

    // Read
    public List<DeveloperDTO> getAll() {
        List<Developer> developers = developerRepository.findAll();
        return developers.stream()
                .map(DeveloperDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<DeveloperDTO> getById(Long id) {
        Optional<Developer> developer = developerRepository.findById(id);
        return developer.map(DeveloperDTO::fromEntity);
    }

    // Update
    @Transactional
    public Optional<DeveloperDTO> update(
            Long id,
            DeveloperDTO updatedDeveloperDTO
    ) {
        Optional<Developer> existingDeveloper = developerRepository.findById(id);
        if (existingDeveloper.isPresent()) {
            Developer developer = existingDeveloper.get();

            // Only update fields that are not null
            if (updatedDeveloperDTO.getName() != null) {
                developer.setName(updatedDeveloperDTO.getName());
            }

            if (updatedDeveloperDTO.getEmail() != null) {
                developer.setEmail(updatedDeveloperDTO.getEmail());
            }

            if (updatedDeveloperDTO.getSkills() != null) {
                developer.setSkills(updatedDeveloperDTO.getSkills());
            }

            Developer savedDeveloper = developerRepository.save(developer);
            return Optional.of(DeveloperDTO.fromEntity(savedDeveloper));
        }
        return Optional.empty();
    }

    // Delete
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return developerRepository.existsById(id);
    }

    // Task relationship management
    @Transactional
    public Optional<DeveloperDTO> assignTask(
            Long developerId,
            Long taskId
    ) {
        Optional<Developer> developerOpt = developerRepository.findById(developerId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if (developerOpt.isPresent() && taskOpt.isPresent()) {
            Developer developer = developerOpt.get();
            Task task = taskOpt.get();

            developer.addTask(task);
            Developer savedDeveloper = developerRepository.save(developer);
            return Optional.of(DeveloperDTO.fromEntity(savedDeveloper));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<DeveloperDTO> removeTask(
            Long developerId,
            Long taskId
    ) {
        Optional<Developer> developerOpt = developerRepository.findById(developerId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if (developerOpt.isPresent() && taskOpt.isPresent()) {
            Developer developer = developerOpt.get();
            Task task = taskOpt.get();

            developer.removeTask(task);
            Developer savedDeveloper = developerRepository.save(developer);
            return Optional.of(DeveloperDTO.fromEntity(savedDeveloper));
        }
        return Optional.empty();
    }
}