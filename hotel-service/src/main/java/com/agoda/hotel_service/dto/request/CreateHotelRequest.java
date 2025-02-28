package com.agoda.hotel_service.dto.request;

import com.agoda.hotel_service.dto.response.RoomDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CreateHotelRequest {
    @NotBlank(message = "Hotel name is required")
    @Size(min = 6, message = "Hotel name must be at least 6 characters")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "VendorId is required")
    private String vendorId;

    @NotBlank(message = "Phone is required")
    private String phone;

    private Set<CreateRoomRequest> rooms;
}
