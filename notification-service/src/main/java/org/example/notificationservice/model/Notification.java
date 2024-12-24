package org.example.notificationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private String userEmail;

}