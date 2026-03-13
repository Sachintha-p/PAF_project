package com.smartcampus.controller;

import com.smartcampus.model.dto.ApiResponse;
import com.smartcampus.model.dto.ResourceRequest;
import com.smartcampus.model.dto.ResourceResponse;
import com.smartcampus.model.enums.ResourceType;
import com.smartcampus.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResourceResponse>>> getAllResources(
            @RequestParam(required = false) ResourceType type,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(ApiResponse.success("Resources fetched", resourceService.getAllResources(type, capacity, location)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceResponse>> getResourceById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Resource fetched", resourceService.getResourceById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<ResourceResponse>> createResource(@Valid @RequestBody ResourceRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Resource created", resourceService.createResource(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<ResourceResponse>> updateResource(@PathVariable Long id, @Valid @RequestBody ResourceRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Resource updated", resourceService.updateResource(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok(ApiResponse.success("Resource deleted", null));
    }
}
