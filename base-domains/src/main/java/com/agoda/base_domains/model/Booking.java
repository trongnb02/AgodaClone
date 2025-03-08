package com.agoda.base_domains.model;

import com.agoda.base_domains.dto.HotelDto;
import com.agoda.base_domains.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Booking implements Serializable {

    private String id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numOfAdults;

    private int numOfChildren;

    private String bookingConfirmationCode;

    private String email;

    private HotelDto hotelDto;

    private RoomDto roomDto;


}
