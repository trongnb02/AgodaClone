package com.agoda.booking_service.service;

import com.agoda.booking_service.dto.BookingDto;
import com.agoda.booking_service.exception.ResourceNotFoundException;
import com.agoda.booking_service.model.Booking;
import com.agoda.booking_service.repository.BookingRepository;
import com.agoda.booking_service.request.CreateNewBooking;
import com.agoda.booking_service.request.UpdateBooking;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public Booking findById(String id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found!"));
    }

    @Override
    public Booking createNewBooking(CreateNewBooking request){
        return bookingRepository.save(Booking.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .numOfAdults(request.getNumOfAdults())
                .numOfChildren(request.getNumOfChildren())
                .hotelId(request.getHotelId())
                .roomId(request.getRoomId())
                .email(request.getEmail())
                .build()
        );
    }

    @Override
    @Transactional
    public Booking updateBooking(UpdateBooking updateBooking) {
        Booking booking = findById(updateBooking.getBookingId());
        modelMapper.map(updateBooking, booking);
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(String bookingId) {
         bookingRepository.delete(findById(bookingId));
    }


}
