package com.agoda.base_domains.event;

import com.agoda.base_domains.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEvent {
    private String message;
    private String status;
    private Booking booking;
}
