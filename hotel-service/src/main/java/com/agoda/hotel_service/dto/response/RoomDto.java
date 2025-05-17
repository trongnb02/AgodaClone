package com.agoda.hotel_service.dto.response;

import com.agoda.hotel_service.model.enums.BedType;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {
    String id;
    String description;
    Double price;
    Integer capacity;
    Boolean availability;
    String hotelId;
    List<String> amenities;
    BedType bedType;
}
