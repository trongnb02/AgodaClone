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
        return ResponseEntity.ok(new ApiResponse("Booking detail",
                    bookingMapper.mapToDto(bookingService.findById(bookingId))));
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> createBooking(@Valid @RequestBody CreateNewBooking request) {
        return ResponseEntity.ok(new ApiResponse("Create new booking successfully!",
                bookingMapper.mapToDto(bookingService.createNewBooking(request))));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateBooking(@Valid @RequestBody UpdateBooking request) {
        return ResponseEntity.ok(new ApiResponse("Update booking successfully!",
                bookingMapper.mapToDto(bookingService.updateBooking(request))));
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok(new ApiResponse("Delete booking successfully!", null));
    }

}
