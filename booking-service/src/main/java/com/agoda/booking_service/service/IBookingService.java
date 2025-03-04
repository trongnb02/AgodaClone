package com.agoda.booking_service.service;

import com.agoda.booking_service.dto.BookingDto;
import com.agoda.booking_service.model.Booking;
import com.agoda.booking_service.request.CreateNewBooking;
import com.agoda.booking_service.request.UpdateBooking;

import java.util.Optional;

public interface IBookingService {
    Booking findById(String id);
    Booking createNewBooking(CreateNewBooking request);
    Booking updateBooking(UpdateBooking updateBooking);
    void deleteBooking(String bookingId);
}
