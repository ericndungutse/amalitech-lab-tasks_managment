package com.ndungutse.project_tracker.controller;

import com.ndungutse.project_tracker.dto.AuditLogDTO;
import com.ndungutse.project_tracker.model.AuditLog;
import com.ndungutse.project_tracker.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    private final AuditService auditService;

    public LogController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> getLogs(
            @RequestParam(required = false) String entityType,
            @RequestParam(required = false) String username
    ) {
        List<AuditLog> logs;

        if (entityType != null && username != null) {
            // Filter by both entity type and username
            logs = auditService.findByEntityTypeAndUsername(entityType, username);
        } else if (entityType != null) {
            // Filter by entity type only
            logs = auditService.findByEntityType(entityType);
        } else if (username != null) {
            // Filter by username only
            logs = auditService.findByUsername(username);
        } else {
            // No filters, return all logs
            logs = auditService.findAll();
        }

        // Convert entities to DTOs
        List<AuditLogDTO> logDTOs = logs.stream()
                .map(AuditLogDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(logDTOs);
    }
}
