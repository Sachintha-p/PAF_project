package com.smartcampus.service.impl;

import com.smartcampus.exception.ResourceNotFoundException;
import com.smartcampus.model.dto.BookingRequest;
import com.smartcampus.model.dto.BookingResponse;
import com.smartcampus.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Override
    public List<BookingResponse> getMyBookings(String email) {
        log.info("Fetching bookings for user: {}", email);
        // TODO: Fetch user bookings
        return Collections.emptyList();
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        log.info("Fetching all bookings");
        // TODO: Fetch all bookings for admin
        return Collections.emptyList();
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        log.info("Fetching booking {}", id);
        // TODO: Implement fetch
        throw new ResourceNotFoundException("Booking not found");
    }

    @Override
    public BookingResponse createBooking(BookingRequest request, String email) {
        log.info("Creating booking for user {} on resource {}", email, request.getResourceId());
        // TODO: Check for conflicting bookings and throw ConflictException if found
        // TODO: Create PENDING booking
        return new BookingResponse();
    }

    @Override
    public BookingResponse approveBooking(Long id) {
        log.info("Approving booking {}", id);
        // TODO: Update status to APPROVED, trigger notification
        return new BookingResponse();
    }

    @Override
    public BookingResponse rejectBooking(Long id, String reason) {
        log.info("Rejecting booking {} with reason: {}", id, reason);
        // TODO: Update status to REJECTED, trigger notification
        return new BookingResponse();
    }

    @Override
    public BookingResponse cancelBooking(Long id, String email) {
        log.info("User {} cancelling booking {}", email, id);
        // TODO: Ensure user owns booking or is ADMIN. Update status to CANCELLED.
        return new BookingResponse();
    }
}
