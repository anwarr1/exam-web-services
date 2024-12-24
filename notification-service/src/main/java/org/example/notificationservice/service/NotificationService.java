package org.example.notificationservice.service;


import org.example.notificationservice.model.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(Notification notification) {
        System.out.println("envoi de notification à " + notification.getUserEmail() + ": " + notification.getMessage());
    }
}