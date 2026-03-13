package com.smartcampus.model.dto;

import com.smartcampus.model.enums.TicketPriority;
import com.smartcampus.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Long id;
    private Long resourceId;
    private String resourceName;
    private Long reportedById;
    private String reportedByName;
    private Long assignedToId;
    private String assignedToName;
    private String category;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private String rejectionReason;
    private String resolutionNotes;
    private String preferredContact;
    private List<String> attachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
