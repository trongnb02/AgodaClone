package com.agoda.hotel_service.dto.request;

import com.agoda.hotel_service.model.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
public class CreateHotelRequest {
    @NotBlank(message = "Hotel name is required")
    @Size(min = 6, message = "Hotel name must be at least 6 characters")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Hotel description is required")
    private String description;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "VendorId is required")
    private String vendorId;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotNull(message = "Property Type is required")
    private PropertyType propertyType;

    private List<String> facilities;

    private LocalTime earliestCheckInTime;
    private LocalTime latestCheckInTime;
    private LocalTime standardCheckOutTime;
    private LocalTime latestCheckOutTime;
    private BigDecimal lateCheckoutFee;

    private Set<CreateRoomRequest> rooms;
}
