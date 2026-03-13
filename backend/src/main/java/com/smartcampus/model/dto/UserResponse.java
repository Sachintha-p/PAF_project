package com.smartcampus.model.dto;

import com.smartcampus.model.enums.Provider;
import com.smartcampus.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String profilePicture;
    private Role role;
    private Provider provider;
    private LocalDateTime createdAt;
}
