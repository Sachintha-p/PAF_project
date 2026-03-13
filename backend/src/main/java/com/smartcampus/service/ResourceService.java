package com.smartcampus.service;

import com.smartcampus.model.dto.ResourceRequest;
import com.smartcampus.model.dto.ResourceResponse;
import com.smartcampus.model.enums.ResourceType;

import java.util.List;

public interface ResourceService {
    List<ResourceResponse> getAllResources(ResourceType type, Integer capacity, String location);
    ResourceResponse getResourceById(Long id);
    ResourceResponse createResource(ResourceRequest request);
    ResourceResponse updateResource(Long id, ResourceRequest request);
    void deleteResource(Long id);
}
