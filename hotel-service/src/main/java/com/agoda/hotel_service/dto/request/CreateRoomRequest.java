package com.agoda.hotel_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoomRequest {
    @NotBlank(message = "roomType is required")
    private String roomType;

    @NotNull
    @Min(value = 0, message = "price must be greater than 0")
    private Double price;

    @NotNull
    @Min(value = 0, message = "capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "availability is required")
    private Boolean availability;
}
