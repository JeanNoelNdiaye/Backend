package com.galimagroup.back.service;

import com.galimagroup.back.entities.Envie;

public interface EnvieService {
    public Envie getEnvieByUser(String userEmail);
    public Envie addProductToEnvie(String userEmail, String productId);
    public Envie removeProductFromEnvie(String userEmail, String productId);
}
