package com.agoda.hotel_service.dto.request;

import com.agoda.hotel_service.model.enums.BedType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateRoomRequest {

    @NotBlank(message = "description is required")
    private String description;

    @NotNull
    @Min(value = 0, message = "price must be greater than 0")
    private Double price;

    @NotNull
    @Min(value = 0, message = "capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "availability is required")
    private Boolean availability;

    private List<String> amenities;

    private BedType bedType;
}
