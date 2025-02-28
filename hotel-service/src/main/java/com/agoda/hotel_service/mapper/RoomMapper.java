package com.agoda.hotel_service.mapper;

import com.agoda.hotel_service.dto.response.RoomDto;
import com.agoda.hotel_service.model.Room;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomMapper {
    private final ModelMapper modelMapper;

    public RoomDto mapToDto(Room room) {
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);
        return roomDto;
    }
}
