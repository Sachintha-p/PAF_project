package com.smartcampus.model.dto;

import com.smartcampus.model.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private TicketStatus status;

    private String resolutionNotes;
    private String rejectionReason;
}
