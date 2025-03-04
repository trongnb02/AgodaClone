package com.agoda.booking_service.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateNewBooking {

    @NotNull(message = "checkInDate is required")
    private LocalDate checkInDate;

    @NotNull(message = "checkOutDate is required")
    private LocalDate checkOutDate;

    @NotNull(message = "numOfAdults is required")
    private int numOfAdults;

    @NotNull(message = "numOfChildren is required")
    private int numOfChildren;

    @NotNull(message = "hotelId is required")
    private String hotelId;

    @NotNull(message = "roomId is required")
    private String roomId;

    @NotNull(message = "email is required")
    private String email;
}
