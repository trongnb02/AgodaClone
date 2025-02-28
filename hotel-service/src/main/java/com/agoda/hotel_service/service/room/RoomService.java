package com.agoda.hotel_service.service.room;

import com.agoda.hotel_service.dto.response.RoomDto;
import com.agoda.hotel_service.exception.ResourceNotFoundException;
import com.agoda.hotel_service.model.Room;
import com.agoda.hotel_service.repository.HotelRepository;
import com.agoda.hotel_service.repository.RoomRepository;
import com.agoda.hotel_service.service.hotel.HotelService;
import com.agoda.hotel_service.service.hotel.IHotelService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService implements IRoomService{
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Room findById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found!"));
    }

    public Room createRoom(RoomDto room) {
        return roomRepository.save(Room.builder()
                .roomType(room.getRoomType())
                .price(room.getPrice())
                .capacity(room.getCapacity())
                .availability(room.getAvailability())
                .hotel(null)
                .build()
        );
    }
}
