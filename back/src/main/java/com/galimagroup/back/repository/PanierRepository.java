package com.galimagroup.back.repository;

import com.galimagroup.back.entities.Panier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends MongoRepository<Panier, String> {
    Panier findByUserEmail(String userEmail); // Permet de trouver le panier par l'email de l'utilisateur
}

