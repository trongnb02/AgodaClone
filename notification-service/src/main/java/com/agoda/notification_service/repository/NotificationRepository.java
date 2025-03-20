package com.agoda.notification_service.repository;

import com.agoda.notification_service.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByEmail(String email);

    @Query("{ 'email': ?0, 'isRead': false }")
    List<Notification> findUnreadByEmail(String email);

    @Query("{ 'type': 'BROADCAST' }")
    List<Notification> findBroadcasts();
}
