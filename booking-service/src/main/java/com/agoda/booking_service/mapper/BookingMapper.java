package com.agoda.booking_service.mapper;

import com.agoda.booking_service.dto.BookingDto;
import com.agoda.booking_service.model.Booking;
import com.agoda.booking_service.service.IBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingMapper {

    private final IBookingService bookingService;

    public BookingDto mapToDto(Booking booking) {
        return BookingDto.builder()
                .bookingId(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .numOfAdults(booking.getNumOfAdults())
                .numOfChildren(booking.getNumOfChildren())
                .hotelId(booking.getHotelId())
                .roomId(booking.getRoomId())
                .email(booking.getEmail())
                .build();
    }

}
