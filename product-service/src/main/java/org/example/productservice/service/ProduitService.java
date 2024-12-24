package org.example.productservice.service;


import org.example.productservice.model.Produit;
import org.example.productservice.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        Optional<Produit> optionalProduit = produitRepository.findById(id);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setName(produitDetails.getName());
            produit.setPrice(produitDetails.getPrice());
            return produitRepository.save(produit);
        } else {
            throw new RuntimeException("Produit not found with id " + id);
        }
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }
}