package org.example.notificationservice.service;

import org.example.notificationservice.model.Notification;
import org.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "commande-topic", groupId = "notification-group")
    public void consume(Notification notification) {
        notificationService.sendNotification(notification);
    }
}