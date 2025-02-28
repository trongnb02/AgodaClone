package com.agoda.hotel_service.controller;

import com.agoda.hotel_service.dto.request.CreateHotelRequest;
import com.agoda.hotel_service.dto.request.CreateRoomRequest;
import com.agoda.hotel_service.dto.response.ApiResponse;
import com.agoda.hotel_service.mapper.HotelMapper;
import com.agoda.hotel_service.mapper.RoomMapper;
import com.agoda.hotel_service.service.hotel.HotelService;
import com.agoda.hotel_service.service.room.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final RoomService roomService;
    private final RoomMapper roomMapper;


    @GetMapping("/test")
    public ResponseEntity<ApiResponse> test() {
        return ResponseEntity.ok(new ApiResponse("Test", null));
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> createHotel(@Valid @RequestBody CreateHotelRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Create Hotel Successfully!", hotelMapper.mapToDto(hotelService.createHotel(request))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/{hotelId}/detail")
    public ResponseEntity<ApiResponse> getHotelDetail(@PathVariable String hotelId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Hotel Detail", hotelMapper.mapToDto(hotelService.findById(hotelId))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAllHotels() {
        try {
            return ResponseEntity.ok(new ApiResponse("All Hotels", hotelService.getAllHotels()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/{hotelid}/addroom")
    public ResponseEntity<ApiResponse> addRoomToHotel(@PathVariable String hotelid, @Valid @RequestBody CreateRoomRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Add new room to hotel successfully!",
                    hotelMapper.mapToDto(hotelService.updateHotel(hotelid, request))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{hotelId}/detail/{roomId}")
    public ResponseEntity<ApiResponse> getRoomById(@PathVariable String roomId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Room Detail", roomMapper.mapToDto(roomService.findById(roomId))));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{hotelId}/roomlist")
    public ResponseEntity<ApiResponse> getAllRoomsOfHotel(@PathVariable String hotelId) {
        try {
            return ResponseEntity.ok(new ApiResponse("List Room Of Hotel",
                    hotelService.getRoomsById(hotelId).stream().map(roomMapper::mapToDto).collect(Collectors.toList())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{hotelId}/delete")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId) {
        try {
            hotelService.deleteHotel(hotelId);
            return ResponseEntity.ok(new ApiResponse("Delete hotel and all its rooms successfully!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
