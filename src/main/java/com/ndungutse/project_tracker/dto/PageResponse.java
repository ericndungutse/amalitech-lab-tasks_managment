package com.ndungutse.project_tracker.dto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public class PageResponse<T> {
    private List<T> content;
    private Map<String, Object> pagination;

    public PageResponse(Page<T> page) {
        this.pagination = Map.of(
                "totalElements", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "currentPage", page.getNumber() + 1,
                "pageSize", page.getSize(),
                "isFirst", page.isFirst(),
                "isLast", page.isLast(),
                "hasNext", page.hasNext(),
                "hasPrevious", page.hasPrevious()
        );
        this.content = page.getContent();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Map<String, Object> getPagination() {
        return pagination;
    }

    public void setPagination(Map<String, Object> pagination) {
        this.pagination = pagination;
    }
}