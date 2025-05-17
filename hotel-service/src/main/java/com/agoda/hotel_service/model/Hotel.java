package com.agoda.hotel_service.model;

import com.agoda.hotel_service.model.enums.PropertyType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String vendorId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "facilities", joinColumns = @JoinColumn(name = "hotels_id"))
    @Column(name = "facility", nullable = false)
    private List<String> facilities = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Room> rooms = new HashSet<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setHotel(this);
    }

    @Column(nullable = false)
    private LocalTime earliestCheckInTime;

    @Column(nullable = false)
    private LocalTime latestCheckInTime;

    @Column(nullable = false)
    private LocalTime standardCheckOutTime;

    @Column(nullable = false)
    private LocalTime latestCheckOutTime;

    @Column(nullable = false)
    private BigDecimal lateCheckoutFee;

    private final static LocalTime DEFAULT_EARLIEST_CHECK_IN = LocalTime.of(7, 0);
    private final static LocalTime DEFAULT_LATEST_CHECK_IN = LocalTime.of(22, 0);
    private final static LocalTime DEFAULT_STANDARD_CHECKOUT = LocalTime.of(11, 0);
    private final static LocalTime DEFAULT_LATEST_CHECKOUT = LocalTime.of(22, 0);
    private final static BigDecimal DEFAULT_LATE_CHECKOUT_FEE = BigDecimal.valueOf(15.95);


    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.setHotel(null);
    }

    public Hotel(String name, String description, String address, String city, String phone, String vendorId,
                 PropertyType propertyType,
                 List<String> facilities) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.vendorId = vendorId;
        this.rooms = new HashSet<>();
        this.propertyType = propertyType;
        this.facilities = facilities;
        this.earliestCheckInTime = DEFAULT_EARLIEST_CHECK_IN;
        this.latestCheckInTime = DEFAULT_LATEST_CHECK_IN;
        this.standardCheckOutTime = DEFAULT_STANDARD_CHECKOUT;
        this.latestCheckOutTime = DEFAULT_LATEST_CHECKOUT;
        this.lateCheckoutFee = DEFAULT_LATE_CHECKOUT_FEE;
    }
}
