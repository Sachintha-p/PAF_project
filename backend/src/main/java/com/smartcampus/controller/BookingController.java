package com.smartcampus.controller;

import com.smartcampus.model.dto.ApiResponse;
import com.smartcampus.model.dto.BookingRequest;
import com.smartcampus.model.dto.BookingResponse;
import com.smartcampus.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookings(Authentication auth, @AuthenticationPrincipal OAuth2User principal) {
        // THE FIX: Explicitly get the email from the Google principal
        String email = principal.getAttribute("email");
        
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ADMIN"));
        
        // Use the 'email' variable instead of auth.getName()
        List<BookingResponse> bookings = isAdmin ? bookingService.getAllBookings() : bookingService.getMyBookings(email);
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched", bookings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Booking fetched", bookingService.getBookingById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request, @AuthenticationPrincipal OAuth2User principal) {
        // THE FIX: Extract email for creating a booking
        String email = principal.getAttribute("email");
        return ResponseEntity.ok(ApiResponse.success("Booking requested", bookingService.createBooking(request, email)));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<BookingResponse>> approveBooking(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Booking approved", bookingService.approveBooking(id)));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<BookingResponse>> rejectBooking(@PathVariable Long id, @RequestParam String reason) {
        return ResponseEntity.ok(ApiResponse.success("Booking rejected", bookingService.rejectBooking(id, reason)));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal) {
        // THE FIX: Extract email for cancelling a booking
        String email = principal.getAttribute("email");
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled", bookingService.cancelBooking(id, email)));
    }
}