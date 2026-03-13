package com.smartcampus.service.impl;

import com.smartcampus.exception.ResourceNotFoundException;
import com.smartcampus.model.dto.ResourceRequest;
import com.smartcampus.model.dto.ResourceResponse;
import com.smartcampus.model.enums.ResourceType;
import com.smartcampus.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Override
    public List<ResourceResponse> getAllResources(ResourceType type, Integer capacity, String location) {
        log.info("Fetching resources. Type: {}, Capacity: {}, Location: {}", type, capacity, location);
        // TODO: Implement filtered fetch
        return Collections.emptyList();
    }

    @Override
    public ResourceResponse getResourceById(Long id) {
        log.info("Fetching resource {}", id);
        // TODO: Implement fetch by ID
        throw new ResourceNotFoundException("Resource not found");
    }

    @Override
    public ResourceResponse createResource(ResourceRequest request) {
        log.info("Creating resource: {}", request.getName());
        // TODO: Implement creation logic
        return new ResourceResponse();
    }

    @Override
    public ResourceResponse updateResource(Long id, ResourceRequest request) {
        log.info("Updating resource: {}", id);
        // TODO: Implement update logic
        return new ResourceResponse();
    }

    @Override
    public void deleteResource(Long id) {
        log.info("Deleting resource: {}", id);
        // TODO: Implement deletion logic
    }
}
