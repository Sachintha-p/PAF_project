package com.smartcampus.controller;

import com.smartcampus.model.dto.ApiResponse;
import com.smartcampus.model.dto.NotificationResponse;
import com.smartcampus.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getMyNotifications(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched", notificationService.getMyNotifications(auth.getName())));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(@PathVariable Long id, Authentication auth) {
        notificationService.markAsRead(id, auth.getName());
        return ResponseEntity.ok(ApiResponse.success("Notification marked as read", null));
    }

    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(Authentication auth) {
        notificationService.markAllAsRead(auth.getName());
        return ResponseEntity.ok(ApiResponse.success("All notifications marked as read", null));
    }
}
