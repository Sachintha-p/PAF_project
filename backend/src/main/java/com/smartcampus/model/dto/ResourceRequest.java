package com.smartcampus.model.dto;

import com.smartcampus.model.enums.ResourceStatus;
import com.smartcampus.model.enums.ResourceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRequest {
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private ResourceType type;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    private String location;

    private String availabilityWindows;

    @NotNull(message = "Status is required")
    private ResourceStatus status;
}
