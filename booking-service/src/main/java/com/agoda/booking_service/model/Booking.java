package com.agoda.booking_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "booking")
@Data
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numOfAdults;

    private int numOfChildren;

    private String bookingConfirmationCode;

    private String email;

    private String hotelId;

    private String roomId;


}
