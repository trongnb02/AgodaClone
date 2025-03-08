package com.agoda.base_domains.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class HotelDto implements Serializable {
    String id;
    String name;
    String address;
    String city;
    String phone;
    String vendorId;
    Set<String> roomsId;
}
