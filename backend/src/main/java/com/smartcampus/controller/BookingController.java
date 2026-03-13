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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookings(Authentication auth) {
        // If ADMIN, fetch all. If user, fetch own. Logic can be handled inside service layer based on role.
        // For scaffold, we assume the service layer checks the authorities of the context, 
        // or we use email to fetch own for user.
        // Since prompt says get own bookings (USER); all bookings (ADMIN), let's defer to service.
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        List<BookingResponse> bookings = isAdmin ? bookingService.getAllBookings() : bookingService.getMyBookings(auth.getName());
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched", bookings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Booking fetched", bookingService.getBookingById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Booking requested", bookingService.createBooking(request, auth.getName())));
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
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled", bookingService.cancelBooking(id, auth.getName())));
    }
}
