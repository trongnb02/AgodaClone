package com.agoda.booking_service.kafka;

import com.agoda.base_domains.event.BookingEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void sendMessage(BookingEvent bookingEvent) {
        LOGGER.info(String.format("Booking event -> %s", bookingEvent));
        Message<BookingEvent> message = MessageBuilder
                .withPayload(bookingEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
