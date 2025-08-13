package com.repairman.repairman.service;

import com.repairman.repairman.exceptions.PurchaseCustomerNotFoundException;
import com.repairman.repairman.model.PurchaseModel;
import com.repairman.repairman.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // Método para devolver TODAS las compras
    public List<PurchaseModel> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    // Método para buscar compras por ID de customer
    public List<PurchaseModel> findByCustomerID(Long customerID){
        List<PurchaseModel> purchases = purchaseRepository.findByCustomerId(customerID);

        // Si no encuentra compras para ese customer, lanza excepción
        if (purchases.isEmpty()) {
            throw new PurchaseCustomerNotFoundException(customerID);
        }

        return purchases;
    }

    // Método para buscar UNA compra por su propio ID
    public PurchaseModel findByPurchaseID(Long purchaseID){
        return purchaseRepository.findByPurchaseID(purchaseID)
                .orElseThrow(() -> new RuntimeException("No se encontró la compra con ID: " + purchaseID));
    }

    // Método para crear una nueva compra
    public PurchaseModel createPurchase(PurchaseModel purchase) {
        return purchaseRepository.save(purchase);
    }

    // Método para actualizar una compra
    public PurchaseModel updatePurchase(Long purchaseID, PurchaseModel updatedPurchase) {
        return purchaseRepository.findByPurchaseID(purchaseID).map(purchase -> {
            purchase.setBrand(updatedPurchase.getBrand());
            purchase.setPhoneStatus(updatedPurchase.getPhoneStatus());
            purchase.setDescription(updatedPurchase.getDescription());
            purchase.setPrice(updatedPurchase.getPrice());
            purchase.setCreatedAt(updatedPurchase.getCreatedAt());
            purchase.setCustomer(updatedPurchase.getCustomer());

            return purchaseRepository.save(purchase);
        }).orElseThrow(() -> new RuntimeException("No se encontró la compra con ID: " + purchaseID));
    }

    // Método para eliminar una compra
    public void deletePurchase(Long purchaseID) {
        if (purchaseRepository.existsById(purchaseID)) {
            purchaseRepository.deleteById(purchaseID);
        } else {
            throw new RuntimeException("No se encontró la compra con ID: " + purchaseID);
        }
    }
}