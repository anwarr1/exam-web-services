package org.example.commandeservice.config;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.example.commandeservice.model.Commande;
import org.example.commandeservice.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommandeResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CommandeService commandeService;

    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeService.getCommandeById(id);
    }

    public Commande addCommande(String productName, int quantity) {
        Commande commande = new Commande();
        commande.setProductName(productName);
        commande.setQuantity(quantity);
        return commandeService.addCommande(commande);
    }

    public Commande updateCommande(Long id, String productName, int quantity) {
        Commande commandeDetails = new Commande();
        commandeDetails.setProductName(productName);
        commandeDetails.setQuantity(quantity);
        return commandeService.updateCommande(id, commandeDetails);
    }

    public boolean deleteCommande(Long id) {
        commandeService.deleteCommande(id);
        return true;
    }
}