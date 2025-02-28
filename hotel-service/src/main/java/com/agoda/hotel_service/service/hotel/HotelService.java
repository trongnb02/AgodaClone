package com.agoda.hotel_service.service.hotel;

import com.agoda.hotel_service.dto.request.CreateHotelRequest;
import com.agoda.hotel_service.exception.ResourceNotFoundException;
import com.agoda.hotel_service.model.Hotel;
import com.agoda.hotel_service.model.Room;
import com.agoda.hotel_service.repository.HotelRepository;
import com.agoda.hotel_service.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class HotelService implements IHotelService{
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel findById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found!"));
    }


    @Transactional
    public Hotel createHotel(CreateHotelRequest request) {
        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .phone(request.getPhone())
                .vendorId(request.getVendorId())
                .rooms(new HashSet<>())
                .build();

        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            request.getRooms().forEach( (room) -> {
                    Room newRoom = Room.builder()
                            .roomType(room.getRoomType())
                            .price(room.getPrice())
                            .capacity(room.getCapacity())
                            .availability(room.getAvailability())
                            .build();
                    hotel.addRoom(newRoom);
                    roomRepository.save(newRoom);

            });
        }
        hotelRepository.save(hotel);
        return hotel;
    }

    public Set<Room> getRoomsById(String id) {
        return hotelRepository.findById(id)
                .map(Hotel::getRooms)
                .orElse(null);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
