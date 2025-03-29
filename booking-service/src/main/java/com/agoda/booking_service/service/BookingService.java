package com.agoda.booking_service.service;

import com.agoda.base_domains.dto.HotelDto;
import com.agoda.base_domains.dto.RoomDto;
import com.agoda.booking_service.client.HotelServiceClient;
import com.agoda.booking_service.exception.ResourceNotFoundException;
import com.agoda.booking_service.kafka.BookingProducer;
import com.agoda.booking_service.mapper.BookingMapper;
import com.agoda.booking_service.model.Booking;
import com.agoda.booking_service.repository.BookingRepository;
import com.agoda.booking_service.request.CreateNewBooking;
import com.agoda.booking_service.request.UpdateBooking;
import com.agoda.booking_service.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final BookingProducer bookingProducer;
    private final BookingMapper bookingMapper;
    private final HotelServiceClient hotelServiceClient;

    @Override
    public Booking findById(String id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found!"));
    }

    @Override
    @Transactional
    public Booking createNewBooking(CreateNewBooking request){
        HotelDto hotelDto = getHotelById(request.getHotelId());
        RoomDto roomDto = getRoomFromFeignClient(request.getHotelId(), request.getRoomId());
        Booking newBooking= bookingRepository.save(Booking.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .numOfAdults(request.getNumOfAdults())
                .numOfChildren(request.getNumOfChildren())
                .hotelId(request.getHotelId())
                .roomId(request.getRoomId())
                .email(request.getEmail())
                .bookingConfirmationCode(new Random().nextInt(1000000) + "")
                .build()
        );

        bookingProducer.sendMessage(bookingMapper.mapToBookingEvent(newBooking, hotelDto, roomDto));
        return newBooking;
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

    private HotelDto getHotelById(String hotelId) {
        ResponseEntity<ApiResponse> responseEntity = hotelServiceClient.getHotelDetail(hotelId);
        HotelDto hotelDto ;
        hotelDto = modelMapper.map(responseEntity.getBody().getData(), HotelDto.class);
        return hotelDto;
    }

    private RoomDto getRoomFromFeignClient(String hotelId, String roomId) {
        ResponseEntity<ApiResponse> responseEntity = hotelServiceClient.getRoomById(hotelId, roomId);
        RoomDto roomDto ;
        roomDto = modelMapper.map(responseEntity.getBody().getData(), RoomDto.class);
        return roomDto;
    }


}
