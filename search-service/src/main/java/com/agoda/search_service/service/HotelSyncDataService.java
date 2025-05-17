package com.agoda.search_service.service;

import com.agoda.search_service.client.HotelClient;
import com.agoda.search_service.exception.ResourceNotFoundException;
import com.agoda.search_service.model.Hotel;
import com.agoda.search_service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelSyncDataService {
    private final HotelRepository hotelRepository;
    private final HotelClient hotelClient;
    private final ModelMapper modelMapper;

    public Hotel getHotelByHotelIdFromHotelService(String hotelId){
        Hotel hotel = modelMapper.map(hotelClient.getHotelById(hotelId).getBody().getData(), Hotel.class);
        return hotel;
    }

    public Hotel createHotel(String hotelId) {
        Hotel hotel = getHotelByHotelIdFromHotelService(hotelId);
        return hotelRepository.save(hotel);
    }

    public void updateHotel(String hotelId) {
        Hotel updateHotel = getHotelByHotelIdFromHotelService(hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()
                -> new ResourceNotFoundException("Hotel with id: " + hotelId + " not found."));

        modelMapper.map(updateHotel, hotel);
        hotelRepository.save(hotel);
    }

    public void deleteHotel(String hotelId) {
        final boolean isHotelExisted = hotelRepository.existsById(hotelId);
        if (isHotelExisted) {
            hotelRepository.deleteById(hotelId);
        } else {
            throw new ResourceNotFoundException("Hotel with id: " + hotelId + " not found.");
        }
    }
}
