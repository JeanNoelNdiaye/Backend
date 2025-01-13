package com.galimagroup.back.repository;

import com.galimagroup.back.entities.Envie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvieRepository extends MongoRepository<Envie, String> {
    Envie findByUserEmail(String userEmail); // Permet de trouver la liste d'envie par l'email de l'utilisateur
}