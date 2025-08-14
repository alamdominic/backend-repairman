package com.repairman.repairman.controller;

import com.repairman.repairman.exceptions.PurchaseCustomerNotFoundException;
import com.repairman.repairman.model.PurchaseModel;
import com.repairman.repairman.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/repairman/purchases")
@CrossOrigin(origins = "*") // habilita las politicas CORS
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Obtener TODAS las compras
    @GetMapping
    public ResponseEntity<?> getAllPurchases(){
        List<PurchaseModel> purchases = purchaseService.getAllPurchases();

        if (purchases.isEmpty()){
            return ResponseEntity.ok("No hay compras registradas");
        }
        return ResponseEntity.ok(purchases);
    }

    // Obtener compras de un customer específico
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getPurchasesByCustomerId(@PathVariable Long customerId) {
        try {
            List<PurchaseModel> purchases = purchaseService.findByCustomerID(customerId);
            return ResponseEntity.ok(purchases);
        } catch (PurchaseCustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Obtener una compra específica por su ID
    @GetMapping("/{purchaseId}")
    public ResponseEntity<?> getPurchaseById(@PathVariable Long purchaseId) {
        try {
            PurchaseModel purchase = purchaseService.findByPurchaseID(purchaseId);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Crear una nueva compra
    @PostMapping
    public ResponseEntity<PurchaseModel> createPurchase(@RequestBody PurchaseModel purchase) {
        PurchaseModel savedPurchase = purchaseService.createPurchase(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPurchase);
    }

    // Actualizar una compra
    @PutMapping("/{purchaseId}")
    public ResponseEntity<?> updatePurchase(@PathVariable Long purchaseId, @RequestBody PurchaseModel updatedPurchase) {
        try {
            PurchaseModel purchase = purchaseService.updatePurchase(purchaseId, updatedPurchase);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Eliminar una compra
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long purchaseId) {
        try {
            purchaseService.deletePurchase(purchaseId);
            return ResponseEntity.ok(Map.of("message", "Compra eliminada exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}