package com.agoda.booking_service.client;

import com.agoda.booking_service.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hotel-service", path = "/api/v1/hotel", configuration = CustomFeignClientConfiguration.class)
public interface HotelServiceClient {
    @GetMapping("/{hotelId}/detail")
    ResponseEntity<ApiResponse> getHotelDetail(@PathVariable String hotelId);

    @GetMapping("/{hotelId}/detail/{roomId}")
    ResponseEntity<ApiResponse> getRoomById(@PathVariable String hotelId,@PathVariable String roomId);
}
