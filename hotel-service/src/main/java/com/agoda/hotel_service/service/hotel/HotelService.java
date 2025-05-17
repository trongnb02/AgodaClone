package com.agoda.hotel_service.service.hotel;

import com.agoda.hotel_service.dto.request.CreateHotelRequest;
import com.agoda.hotel_service.dto.request.CreateRoomRequest;
import com.agoda.hotel_service.exception.ResourceNotFoundException;
import com.agoda.hotel_service.model.Hotel;
import com.agoda.hotel_service.model.Room;
import com.agoda.hotel_service.repository.HotelRepository;
import com.agoda.hotel_service.repository.RoomRepository;
import com.agoda.hotel_service.service.room.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class HotelService implements IHotelService{
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final IRoomService roomService;

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel findById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found!"));
    }


    @Transactional
    public Hotel createHotel(CreateHotelRequest request) {
        Hotel hotel = new Hotel(request.getName(),
                request.getDescription(),
                request.getAddress(),
                request.getCity(),
                request.getPhone(),
                request.getVendorId(),
                request.getPropertyType(),
                request.getFacilities());

        if (request.getEarliestCheckInTime() != null) {
            hotel.setEarliestCheckInTime(request.getEarliestCheckInTime());
        }
        if (request.getLatestCheckInTime() != null) {
            hotel.setLatestCheckInTime(request.getLatestCheckInTime());
        }
        if (request.getStandardCheckOutTime() != null) {
            hotel.setStandardCheckOutTime(request.getStandardCheckOutTime());
        }
        if (request.getLatestCheckOutTime() != null) {
            hotel.setLatestCheckOutTime(request.getLatestCheckOutTime());
        }
        if (request.getLateCheckoutFee() != null) {
            hotel.setLateCheckoutFee(request.getLateCheckoutFee());
        }

        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            request.getRooms().forEach( (room) -> {
                    Room newRoom = roomService.createRoom(room, hotel);
                    hotel.addRoom(newRoom);
                    roomRepository.save(newRoom);

            });
        }
        hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public Set<Room> getRoomsById(String id) {
        return hotelRepository.findById(id)
                .map(Hotel::getRooms)
                .orElse(null);
    }


    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Transactional
    public Hotel addRoomToHotel(String hotelId, CreateRoomRequest request) {
        Hotel hotel = findById(hotelId);
        Room newRoom = roomService.createRoom(request, hotel);
        hotel.addRoom(newRoom);
        roomRepository.save(newRoom);
        return hotelRepository.save(hotel);
    }
}
