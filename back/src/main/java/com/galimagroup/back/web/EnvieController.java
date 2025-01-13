package com.galimagroup.back.web;

import com.galimagroup.back.entities.Envie;
import com.galimagroup.back.service.EnvieService;
import com.galimagroup.back.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envie")
public class EnvieController {

    @Autowired
    private EnvieService envieService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add/{productId}")
    public Envie addProductToEnvie(@PathVariable String productId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);

        return envieService.addProductToEnvie(email, productId);
    }

    @PostMapping("/remove/{productId}")
    public Envie removeProductFromEnvie(@PathVariable String productId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);

        return envieService.removeProductFromEnvie(email, productId);
    }

    @GetMapping
    public Envie getEnvie(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return envieService.getEnvieByUser(email);
    }
}
