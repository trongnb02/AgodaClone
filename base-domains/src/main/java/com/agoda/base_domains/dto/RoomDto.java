package com.agoda.base_domains.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomDto implements Serializable {
    String id;
    String roomType;
    Double price;
    Integer capacity;
    Boolean availability;
    String hotelId;
}
