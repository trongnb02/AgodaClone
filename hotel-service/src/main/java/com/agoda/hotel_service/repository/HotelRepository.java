package com.agoda.hotel_service.repository;

import com.agoda.hotel_service.model.Hotel;
import com.agoda.hotel_service.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    Optional<Hotel> findById(String id);
}
