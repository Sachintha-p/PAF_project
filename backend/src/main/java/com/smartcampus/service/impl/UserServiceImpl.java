package com.smartcampus.service.impl;

import com.smartcampus.model.dto.UserResponse;
import com.smartcampus.model.enums.Role;
import com.smartcampus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public UserResponse getCurrentUser(String email) {
        // TODO: Implement user fetching logic mapped to DTO
        log.info("Fetching current user for: {}", email);
        return new UserResponse();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        // TODO: Implement fetch all users
        log.info("Fetching all users");
        return Collections.emptyList();
    }

    @Override
    public UserResponse changeUserRole(Long id, Role role) {
        // TODO: Implement updating user role
        log.info("Updating role for user {} to {}", id, role);
        return new UserResponse();
    }
}
