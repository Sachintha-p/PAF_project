package com.smartcampus.controller;

import com.smartcampus.model.dto.ApiResponse;
import com.smartcampus.model.dto.UserResponse;
import com.smartcampus.model.enums.Role;
import com.smartcampus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser(Authentication auth) {
        String email = auth.getName(); // Depending on JWT/OAuth token format
        return ResponseEntity.ok(ApiResponse.success("Current user fetched", userService.getCurrentUser(email)));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success("All users fetched", userService.getAllUsers()));
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> changeUserRole(@PathVariable Long id, @RequestParam Role role) {
        return ResponseEntity.ok(ApiResponse.success("User role updated", userService.changeUserRole(id, role)));
    }
}
