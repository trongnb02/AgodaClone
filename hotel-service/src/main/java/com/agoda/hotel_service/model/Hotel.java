package com.agoda.hotel_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    String name;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String vendorId;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Room> rooms = new HashSet<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setHotel(this);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.setHotel(null);
    }
}
