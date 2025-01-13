package com.galimagroup.back.serviceImpl;

import com.galimagroup.back.entities.Panier;
import com.galimagroup.back.entities.Product;
import com.galimagroup.back.repository.PanierRepository;
import com.galimagroup.back.repository.ProductRepository;
import com.galimagroup.back.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Panier getPanierByUser(String userEmail) {
        return panierRepository.findByUserEmail(userEmail);
    }

    @Override
    public Panier addProductToPanier(String userEmail, String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Panier panier = getPanierByUser(userEmail);
            if (panier == null) {
                panier = new Panier(userEmail);
                System.out.println("Email extrait : " + panier.getUserEmail());
            }
            panier.getProductIds().add(productId);
            return panierRepository.save(panier);
        }
        return null;
    }

    @Override
    public Panier removeProductFromPanier(String userEmail, String productId) {
        Panier panier = getPanierByUser(userEmail);
        if (panier != null) {
            panier.getProductIds().remove(productId);
            return panierRepository.save(panier);
        }
        return null;
    }
}
