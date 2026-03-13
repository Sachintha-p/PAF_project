package com.smartcampus.model.dto;

import com.smartcampus.model.enums.ResourceStatus;
import com.smartcampus.model.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {
    private Long id;
    private String name;
    private ResourceType type;
    private Integer capacity;
    private String location;
    private String availabilityWindows;
    private ResourceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
