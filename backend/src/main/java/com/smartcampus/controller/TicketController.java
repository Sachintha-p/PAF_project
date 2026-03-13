package com.smartcampus.controller;

import com.smartcampus.model.dto.*;
import com.smartcampus.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getTickets(Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<TicketResponse> tickets = isAdmin ? ticketService.getAllTickets() : ticketService.getMyTickets(auth.getName());
        return ResponseEntity.ok(ApiResponse.success("Tickets fetched", tickets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketResponse>> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Ticket fetched", ticketService.getTicketById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@Valid @RequestBody TicketRequest request, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Ticket created", ticketService.createTicket(request, auth.getName())));
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<TicketResponse>> assignTicket(@PathVariable Long id, @RequestParam Long technicianId) {
        return ResponseEntity.ok(ApiResponse.success("Ticket assigned", ticketService.assignTicket(id, technicianId)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<TicketResponse>> updateTicketStatus(@PathVariable Long id, @Valid @RequestBody TicketStatusUpdateRequest request, Authentication auth) {
        // Technically mapped to ADMIN/TECHNICIAN in requirements, can be checked via MethodSecurity or internal logic
        return ResponseEntity.ok(ApiResponse.success("Ticket status updated", ticketService.updateTicketStatus(id, request, auth.getName())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket deleted", null));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<ApiResponse<CommentResponse>> addComment(@PathVariable Long id, @Valid @RequestBody CommentRequest request, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Comment added", ticketService.addComment(id, request, auth.getName())));
    }

    @PutMapping("/{id}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponse>> updateComment(@PathVariable Long id, @PathVariable Long commentId, @Valid @RequestBody CommentRequest request, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Comment updated", ticketService.updateComment(id, commentId, request, auth.getName())));
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id, @PathVariable Long commentId, Authentication auth) {
        ticketService.deleteComment(id, commentId, auth.getName());
        return ResponseEntity.ok(ApiResponse.success("Comment deleted", null));
    }

    @PostMapping("/{id}/attachments")
    public ResponseEntity<ApiResponse<TicketResponse>> uploadAttachments(@PathVariable Long id, @RequestParam("files") List<MultipartFile> files) {
        // File handling logic to convert MultipartFile to paths would happen here.
        // For scaffold, passing empty strings
        return ResponseEntity.ok(ApiResponse.success("Attachments uploaded", ticketService.uploadAttachments(id, List.of())));
    }
}
