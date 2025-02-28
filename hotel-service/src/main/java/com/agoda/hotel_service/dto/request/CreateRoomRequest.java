package com.agoda.hotel_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CreateRoomRequest {
    @NotBlank(message = "roomType is required")
    private String roomType;

    @NotBlank(message = "price is required")
    private Double price;

    @NotBlank(message = "capacity is required")
    private Integer capacity;

    @NotBlank(message = "availability is required")
    private Boolean availability;
}
