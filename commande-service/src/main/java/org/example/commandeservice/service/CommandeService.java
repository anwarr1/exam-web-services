package org.example.commandeservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.commandeservice.model.Commande;
import org.example.commandeservice.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackAddCommande")
    public Commande addCommande(Commande commande) {
        // Simulate a call to the product service
        // productService.checkProductAvailability(commande.getProductId());

        Commande savedCommande = commandeRepository.save(commande);
        kafkaProducer.sendMessage(savedCommande);
        return savedCommande;
    }

    public Commande fallbackAddCommande(Commande commande, Throwable throwable) {
        // Fallback logic
        System.out.println("Fallback method called due to: " + throwable.getMessage());
        return new Commande(); // Return a default or empty Commande object
    }

    public Commande updateCommande(Long id, Commande commandeDetails) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setProductName(commandeDetails.getProductName());
            commande.setQuantity(commandeDetails.getQuantity());
            return commandeRepository.save(commande);
        } else {
            throw new RuntimeException("Commande not found with id " + id);
        }
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }
}