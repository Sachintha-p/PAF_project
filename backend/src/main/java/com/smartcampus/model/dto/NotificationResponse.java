package com.smartcampus.model.dto;

import com.smartcampus.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private Long recipientId;
    private NotificationType type;
    private String message;
    private Boolean isRead;
    private Long relatedEntityId;
    private String relatedEntityType;
    private LocalDateTime createdAt;
}
