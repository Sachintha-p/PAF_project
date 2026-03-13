package com.smartcampus.repository;

import com.smartcampus.model.entity.Ticket;
import com.smartcampus.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByReportedByIdOrderByCreatedAtDesc(Long reportedById);
    List<Ticket> findByAssignedToIdOrderByCreatedAtDesc(Long assignedToId);
    List<Ticket> findByStatus(TicketStatus status);
}
