package com.agoda.hotel_service.mapper;

import com.agoda.hotel_service.dto.response.HotelDto;
import com.agoda.hotel_service.model.Hotel;
import com.agoda.hotel_service.model.Room;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelMapper {
    private final ModelMapper modelMapper;

    public HotelDto mapToDto(Hotel hotel) {
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
        Set<Room> rooms = hotel.getRooms();

        Set<String> roomsId = new HashSet<>();
        if (rooms != null) {
            roomsId = rooms.stream()
                    .map(Room::getId)
                    .collect(Collectors.toSet());
        }

        hotelDto.setRoomsId(roomsId);
        return hotelDto;
    }
}
