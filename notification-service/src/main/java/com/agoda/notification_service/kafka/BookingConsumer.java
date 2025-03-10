package com.agoda.notification_service.kafka;

import com.agoda.base_domains.event.BookingEvent;
import com.agoda.notification_service.dto.EmailRequest;
import com.agoda.notification_service.service.EmailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BookingConsumer {

    @Autowired
    private EmailSenderService emailSenderService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BookingEvent bookingEvent) {
        log.info("Received booking event: " + bookingEvent);


        emailSenderService.sendEmail(
                new EmailRequest(
                        bookingEvent.getBooking().getEmail(),
                        "[AGODA-CLONE] Your Booking Details [ID: " + bookingEvent.getBooking().getId() + "]",
                        generateEmailContent(bookingEvent)
                )
        );
    }

    public String generateEmailContent(BookingEvent bookingEvent) {
        String text = """
                Dear %s, \s
                Your hotel booking has been successfully created. Below are the details: \s
                Booking ID: %s \s
                Check-In Date: %s \s
                Check-Out Date: %s \s
                Number of Adults: %d \s
                Number of Children: %d \s
                Hotel: %s (ID: %s) \s
                Address: %s \s
                Room: %s (ID: %s) \s
                If you have any questions, please contact us. \s
                Sincerely, \s
                """;

        return  String.format(text,
                "Customer",
                bookingEvent.getBooking().getId(),
                bookingEvent.getBooking().getCheckInDate().toString(),
                bookingEvent.getBooking().getCheckOutDate().toString(),
                bookingEvent.getBooking().getNumOfAdults(),
                bookingEvent.getBooking().getNumOfChildren(),
                bookingEvent.getBooking().getHotelDto().getName(),
                bookingEvent.getBooking().getHotelDto().getId(),
                bookingEvent.getBooking().getHotelDto().getAddress(),
                bookingEvent.getBooking().getRoomDto().getRoomType(),
                bookingEvent.getBooking().getRoomDto().getId()
        );
    }

}
