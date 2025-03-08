package com.agoda.booking_service.controller;

import com.agoda.base_domains.event.BookingEvent;
import com.agoda.booking_service.kafka.BookingProducer;
import com.agoda.booking_service.mapper.BookingMapper;
import com.agoda.booking_service.model.Booking;
import com.agoda.booking_service.request.CreateNewBooking;
import com.agoda.booking_service.request.UpdateBooking;
import com.agoda.booking_service.response.ApiResponse;
import com.agoda.booking_service.service.IBookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final IBookingService bookingService;
    private final BookingMapper bookingMapper;
    private final BookingProducer bookingProducer;

    @GetMapping("/detail/{bookingId}")
    public ResponseEntity<ApiResponse> getBookingById(@PathVariable String bookingId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Booking detail",
                    bookingMapper.mapToDto(bookingService.findById(bookingId))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> createBooking(@Valid @RequestBody CreateNewBooking request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Create new booking successfully!",
                    bookingMapper.mapToDto(bookingService.createNewBooking(request))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateBooking(@Valid @RequestBody UpdateBooking request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Update booking successfully!",
                    bookingMapper.mapToDto(bookingService.updateBooking(request))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable String bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            return ResponseEntity.ok(new ApiResponse("Delete booking successfully!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
