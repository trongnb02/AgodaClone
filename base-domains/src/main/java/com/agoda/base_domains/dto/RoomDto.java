package com.agoda.base_domains.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomDto implements Serializable {
    String id;
    Double price;
    Integer capacity;
    Boolean availability;
    String hotelId;
}
