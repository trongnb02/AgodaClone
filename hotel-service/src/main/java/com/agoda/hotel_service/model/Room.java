package com.agoda.hotel_service.model;

import com.agoda.hotel_service.model.enums.BedType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room extends BaseEntity{

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Boolean availability = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference
    private Hotel hotel;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "rooms_id"))
    @Column(name = "amenity", nullable = false)
    private List<String> amenities = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BedType bedType;

}
