package com.agoda.hotel_service.service.hotel;

import com.agoda.hotel_service.dto.request.CreateHotelRequest;
import com.agoda.hotel_service.model.Hotel;
import com.agoda.hotel_service.model.Room;

import java.util.List;
import java.util.Set;

public interface IHotelService {
    Hotel save(Hotel hotel);
    Hotel findById(String id);
    Hotel createHotel(CreateHotelRequest request);
    Set<Room> getRoomsById(String id);
    List<Hotel> getAllHotels();
}
