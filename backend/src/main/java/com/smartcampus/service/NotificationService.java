package com.smartcampus.service;

import com.smartcampus.model.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getMyNotifications(String email);
    void markAsRead(Long notificationId, String email);
    void markAllAsRead(String email);
    void createNotification(Long recipientId, com.smartcampus.model.enums.NotificationType type, String message, Long relatedEntityId, String relatedEntityType);
}
