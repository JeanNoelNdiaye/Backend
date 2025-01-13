package com.galimagroup.back.web;

import com.galimagroup.back.entities.Panier;
import com.galimagroup.back.service.PanierService;
import com.galimagroup.back.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add/{productId}")
    public Panier addProductToPanier(@PathVariable String productId, String email) {
        /*String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);*/

        return panierService.addProductToPanier(email, productId);
    }

    @PostMapping("/remove/{productId}")
    public Panier removeProductFromPanier(@PathVariable String productId, String email) {
        //String token = authorizationHeader.substring(7);  // Retire "Bearer "
        //String email = jwtUtil.extractEmail(token);

        return panierService.removeProductFromPanier(email, productId);
    }

    @GetMapping
    public Panier getPanier(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return panierService.getPanierByUser(email);
    }
}
