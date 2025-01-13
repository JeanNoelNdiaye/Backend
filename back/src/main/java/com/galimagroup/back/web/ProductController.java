package com.galimagroup.back.web;

import com.galimagroup.back.entities.Product;
import com.galimagroup.back.security.JwtUtil;
import com.galimagroup.back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    /*@PostMapping
    public Product createProduct(@RequestBody Product product) {
        ret
        urn productService.createProduct(product);
    }*/
    // Exemple d'endpoint pour créer un produit, uniquement accessible par l'admin
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestHeader("Authorization") String authorizationHeader) {
        // Extraire l'email de l'utilisateur à partir du token JWT
        String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);
        System.out.println("Email extrait : " + email);
        // Vérifie si l'utilisateur est l'admin
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Interdit si ce n'est pas l'admin
        }

        // Si l'utilisateur est l'admin, crée le produit
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }*/


    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }*/


    // Exemple pour mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product, @RequestHeader("Authorization") String authorizationHeader) {
        // Extraire l'email de l'utilisateur
        String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);

        // Vérifie si l'utilisateur est l'admin
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Interdit si ce n'est pas l'admin
        }

        // Si l'utilisateur est l'admin, met à jour le produit
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    // Exemple pour supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id, @RequestHeader("Authorization") String authorizationHeader) {
        // Extraire l'email de l'utilisateur
        String token = authorizationHeader.substring(7);  // Retire "Bearer "
        String email = jwtUtil.extractEmail(token);

        // Vérifie si l'utilisateur est l'admin
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Interdit si ce n'est pas l'admin
        }

        // Si l'utilisateur est l'admin, supprime le produit
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

