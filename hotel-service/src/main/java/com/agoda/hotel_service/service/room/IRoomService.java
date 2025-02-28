package com.agoda.hotel_service.service.room;

import com.agoda.hotel_service.dto.response.RoomDto;
import com.agoda.hotel_service.model.Room;

public interface IRoomService {
    Room save(Room room);
    Room findById(String id);
    Room createRoom(RoomDto room);
}
