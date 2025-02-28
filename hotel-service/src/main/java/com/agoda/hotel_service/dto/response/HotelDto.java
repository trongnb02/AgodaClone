package com.agoda.hotel_service.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class HotelDto {
    String id;
    String name;
    String address;
    String city;
    String phone;
    String vendorId;
    Set<String> roomsId;
}
