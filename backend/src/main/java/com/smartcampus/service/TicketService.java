package com.smartcampus.service;

import com.smartcampus.model.dto.CommentRequest;
import com.smartcampus.model.dto.CommentResponse;
import com.smartcampus.model.dto.TicketRequest;
import com.smartcampus.model.dto.TicketResponse;
import com.smartcampus.model.dto.TicketStatusUpdateRequest;

import java.util.List;

public interface TicketService {
    List<TicketResponse> getMyTickets(String email);
    List<TicketResponse> getAllTickets();
    TicketResponse getTicketById(Long id);
    TicketResponse createTicket(TicketRequest request, String email);
    TicketResponse assignTicket(Long id, Long technicianId);
    TicketResponse updateTicketStatus(Long id, TicketStatusUpdateRequest request, String email);
    void deleteTicket(Long id);
    
    CommentResponse addComment(Long ticketId, CommentRequest request, String email);
    CommentResponse updateComment(Long ticketId, Long commentId, CommentRequest request, String email);
    void deleteComment(Long ticketId, Long commentId, String email);
    
    TicketResponse uploadAttachments(Long ticketId, List<String> filePaths);
}
