package com.galimagroup.back.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "paniers")
public class Panier {
    @Id
    private String id;

    private String userEmail;  // Utilisation de l'email pour lier l'utilisateur au panier

    private Set<String> productIds = new HashSet<>();  // Stocke les ID des produits dans le panier

    public Panier() {}

    public Panier(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<String> productIds) {
        this.productIds = productIds;
    }
}