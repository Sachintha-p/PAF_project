package com.smartcampus.service.impl;

import com.smartcampus.exception.ResourceNotFoundException;
import com.smartcampus.model.dto.CommentRequest;
import com.smartcampus.model.dto.CommentResponse;
import com.smartcampus.model.dto.TicketRequest;
import com.smartcampus.model.dto.TicketResponse;
import com.smartcampus.model.dto.TicketStatusUpdateRequest;
import com.smartcampus.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Override
    public List<TicketResponse> getMyTickets(String email) {
        log.info("Fetching tickets for user: {}", email);
        // TODO: Implement
        return Collections.emptyList();
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        log.info("Fetching all tickets");
        // TODO: Implement
        return Collections.emptyList();
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        log.info("Fetching ticket {}", id);
        // TODO: Implement
        throw new ResourceNotFoundException("Ticket not found");
    }

    @Override
    public TicketResponse createTicket(TicketRequest request, String email) {
        log.info("Creating ticket by user {}", email);
        // TODO: Implement
        return new TicketResponse();
    }

    @Override
    public TicketResponse assignTicket(Long id, Long technicianId) {
        log.info("Assigning ticket {} to technician {}", id, technicianId);
        // TODO: Implement assignment 
        return new TicketResponse();
    }

    @Override
    public TicketResponse updateTicketStatus(Long id, TicketStatusUpdateRequest request, String email) {
        log.info("Updating ticket {} status to {}", id, request.getStatus());
        // TODO: Implement status update + trigger notification
        return new TicketResponse();
    }

    @Override
    public void deleteTicket(Long id) {
        log.info("Deleting ticket {}", id);
        // TODO: Implement
    }

    @Override
    public CommentResponse addComment(Long ticketId, CommentRequest request, String email) {
        log.info("Adding comment to ticket {} by user {}", ticketId, email);
        // TODO: Implement add comment + trigger notification
        return new CommentResponse();
    }

    @Override
    public CommentResponse updateComment(Long ticketId, Long commentId, CommentRequest request, String email) {
        log.info("Updating comment {}", commentId);
        // TODO: Ensure ownership, implement update
        return new CommentResponse();
    }

    @Override
    public void deleteComment(Long ticketId, Long commentId, String email) {
        log.info("Deleting comment {}", commentId);
        // TODO: Ensure ownership or ADMIN, implement delete
    }

    @Override
    public TicketResponse uploadAttachments(Long ticketId, List<String> filePaths) {
        log.info("Uploading attachments to ticket {}", ticketId);
        // TODO: Validate max 3 files, save to DB collection
        return new TicketResponse();
    }
}
