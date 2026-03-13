package com.smartcampus.service.impl;

import com.smartcampus.model.dto.NotificationResponse;
import com.smartcampus.model.enums.NotificationType;
import com.smartcampus.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public List<NotificationResponse> getMyNotifications(String email) {
        log.info("Fetching notifications for user {}", email);
        // TODO: Fetch unread notifications first, ordered by date
        return Collections.emptyList();
    }

    @Override
    public void markAsRead(Long notificationId, String email) {
        log.info("Marking notification {} as read for user {}", notificationId, email);
        // TODO: Implement
    }

    @Override
    public void markAllAsRead(String email) {
        log.info("Marking all notifications as read for user {}", email);
        // TODO: Implement
    }

    @Override
    public void createNotification(Long recipientId, NotificationType type, String message, Long relatedEntityId, String relatedEntityType) {
        log.info("Creating {} notification for user {}", type, recipientId);
        // TODO: Build and save entity
    }
}
