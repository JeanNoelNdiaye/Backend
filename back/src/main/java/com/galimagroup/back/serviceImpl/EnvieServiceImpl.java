package com.galimagroup.back.serviceImpl;

import com.galimagroup.back.entities.Envie;
import com.galimagroup.back.entities.Product;
import com.galimagroup.back.repository.EnvieRepository;
import com.galimagroup.back.repository.ProductRepository;
import com.galimagroup.back.service.EnvieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnvieServiceImpl implements EnvieService {

    @Autowired
    private EnvieRepository envieRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Envie getEnvieByUser(String userEmail) {
        return envieRepository.findByUserEmail(userEmail);
    }

    @Override
    public Envie addProductToEnvie(String userEmail, String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Envie envie = getEnvieByUser(userEmail);
            if (envie == null) {
                envie = new Envie(userEmail);
            }
            envie.getProductIds().add(productId);
            return envieRepository.save(envie);
        }
        return null;
    }

    @Override
    public Envie removeProductFromEnvie(String userEmail, String productId) {
        Envie envie = getEnvieByUser(userEmail);
        if (envie != null) {
            envie.getProductIds().remove(productId);
            return envieRepository.save(envie);
        }
        return null;
    }
}
