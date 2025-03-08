package com.agoda.booking_service.mapper;

import com.agoda.base_domains.dto.HotelDto;
import com.agoda.base_domains.dto.RoomDto;
import com.agoda.base_domains.event.BookingEvent;
import com.agoda.booking_service.dto.BookingDto;
import com.agoda.booking_service.model.Booking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingMapper {

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

    public BookingEvent mapToBookingEvent(Booking booking, HotelDto hotelDto, RoomDto roomDto) {
        return new BookingEvent(
                "PENDING",
                "Booking status is in pending state",
                com.agoda.base_domains.model.Booking.builder()
                        .id(booking.getId())
                        .checkInDate(booking.getCheckInDate())
                        .checkOutDate(booking.getCheckOutDate())
                        .numOfAdults(booking.getNumOfAdults())
                        .numOfChildren(booking.getNumOfChildren())
                        .hotelDto(hotelDto)
                        .roomDto(roomDto)
                        .email(booking.getEmail())
                        .build()
        );
    }

}
