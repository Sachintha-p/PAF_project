package com.smartcampus.repository;

import com.smartcampus.model.entity.Resource;
import com.smartcampus.model.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByType(ResourceType type);
    List<Resource> findByCapacityGreaterThanEqual(Integer capacity);
    List<Resource> findByLocationContainingIgnoreCase(String location);
}
