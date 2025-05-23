package com.agoda.notification_service.kafka;

import com.agoda.base_domains.event.BookingEvent;
import com.agoda.notification_service.dto.request.EmailRequest;
import com.agoda.notification_service.dto.request.NotificationRequest;
import com.agoda.notification_service.service.EmailSenderService;
import com.agoda.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BookingConsumer {

    @Autowired
    private EmailSenderService emailSenderService;
    private NotificationService notificationService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BookingEvent bookingEvent) {
        log.info("Received booking event: " + bookingEvent);

        String title = "[AGODA-CLONE] Your Booking Details [ID: " + bookingEvent.getBooking().getId() + "]";
        String content = generateContent(bookingEvent);
        String email = bookingEvent.getBooking().getEmail();

        emailSenderService.sendEmail(
                new EmailRequest(email, title, content)
        );

        notificationService.createOwnerSpecificNotification(
                new NotificationRequest(email, title, generateContent(bookingEvent))
        );

    }

    public String generateContent(BookingEvent bookingEvent) {
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
                bookingEvent.getBooking().getRoomDto().getId()
        );
    }

}
