package com.agoda.hotel_service.dto.response;

import lombok.Data;

@Data
public class RoomDto {
    String id;
    String roomType;
    Double price;
    Integer capacity;
    Boolean availability;
    String hotelId;
}
