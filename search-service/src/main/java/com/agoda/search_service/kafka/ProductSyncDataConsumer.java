package com.agoda.search_service.kafka;

import com.agoda.base_domains.cdc.HotelCdcMessage;
import com.agoda.base_domains.cdc.MessageKey;
import com.agoda.base_domains.cdc.Operation;
import com.agoda.search_service.service.HotelSyncDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductSyncDataConsumer {
    private final HotelSyncDataService hotelSyncDataService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void processMessage(HotelCdcMessage hotelCdcMessage, @Header(KafkaHeaders.RECEIVED_KEY) MessageKey key) {
        log.info("Received message: {}", hotelCdcMessage);
        boolean isHardDeleteEvent = Operation.DELETE.equals(hotelCdcMessage.getOp());
        if (isHardDeleteEvent) {
            hotelSyncDataService.deleteHotel(key.getId());
        } else {
            var operation = hotelCdcMessage.getOp();
            var hotelId = key.getId();
            switch (operation) {
                case CREATE, READ -> hotelSyncDataService.createHotel(hotelId);
                case UPDATE -> hotelSyncDataService.updateHotel(hotelId);
                default -> log.warn("Unsupported operation '{}' for: '{}'", operation, hotelId);
            }
        }
    }
}
