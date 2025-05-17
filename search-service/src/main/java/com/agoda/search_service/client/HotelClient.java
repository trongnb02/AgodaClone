package com.agoda.search_service.client;

import com.agoda.search_service.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hotel-service", path = "/api/v1/hotel")
public interface HotelClient {
    @GetMapping("/{hotelId}/detail-es")
    ResponseEntity<ApiResponse> getHotelById(@PathVariable String hotelId);
}
