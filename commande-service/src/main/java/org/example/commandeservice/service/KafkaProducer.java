package org.example.commandeservice.service;

import org.example.commandeservice.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "commande-topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Commande commande) {
        kafkaTemplate.send(TOPIC, commande);
    }
}