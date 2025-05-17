package com.agoda.hotel_service.dto.response;

import com.agoda.hotel_service.model.enums.PropertyType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
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
    String description;
    PropertyType propertyType;
    Set<String> facilities;
    LocalTime earliestCheckInTime;
    LocalTime latestCheckInTime;
    LocalTime standardCheckOutTime;
    LocalTime latestCheckOutTime;
    BigDecimal lateCheckoutFee;
}
