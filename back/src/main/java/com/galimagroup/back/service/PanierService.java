package com.galimagroup.back.service;

import com.galimagroup.back.entities.Panier;

public interface PanierService {
    public Panier getPanierByUser(String userEmail);
    public Panier addProductToPanier(String userEmail, String productId);
    public Panier removeProductFromPanier(String userEmail, String productId);
}
