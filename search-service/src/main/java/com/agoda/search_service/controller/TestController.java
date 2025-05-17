package com.agoda.search_service.controller;

import com.agoda.search_service.model.Hotel;
import com.agoda.search_service.service.HotelSyncDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class TestController {

    private final HotelSyncDataService hotelSyncDataService;

    @GetMapping("/{hotelId}")
    public Hotel test(@PathVariable String hotelId) {
        return hotelSyncDataService.createHotel(hotelId);
    }

}
