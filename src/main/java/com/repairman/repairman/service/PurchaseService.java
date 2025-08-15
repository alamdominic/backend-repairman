package com.repairman.repairman.service;

import com.repairman.repairman.exceptions.CustomerNotFoundException;
import com.repairman.repairman.exceptions.PurchaseCustomerNotFoundException;
import com.repairman.repairman.exceptions.PurchaseNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.PurchaseModel;
import com.repairman.repairman.repository.CustomerRepository;
import com.repairman.repairman.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    // Se inyecta para hacer validaciones
    private final CustomerRepository customerRepository;

    //constructor de la clase CustomerService y su función es recibir una dependencia (CustomerRepository)
    // y asignarla al campo interno de la clase para poder usarla.
    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
    }

    // Método para devolver todos los purchases
    public List<PurchaseModel> getPurchases(){
        return purchaseRepository.findAll();
    }

    // Método para devolver un único customer una Exception
    public PurchaseModel findById (Long id){
        // lanzamos una exception usando lambda orElseThrow
        return purchaseRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    // Método para crear nuevo Compra
    public CustomerModel createCustomer(CustomerModel newCustomer){
        return customerRepository.save(newCustomer);
    }

    // Método para crear una nueva compra
    public PurchaseModel addPurchase(PurchaseModel newPurchase) {
        CustomerModel customerPurchase = newPurchase.getCustomer();

        // Busca al Customer en la base de datos usando su ID
        CustomerModel existingCustomer = customerRepository.findById(customerPurchase.getCustomerID())
                .orElseThrow(() -> new CustomerNotFoundException(customerPurchase.getCustomerID())); // <-- Maneja el caso si el cliente no existe

        newPurchase.setCustomer(existingCustomer); // Asocia el customerID con la una nueva Sale
        return purchaseRepository.save(newPurchase);
    }

    // Recuperar una venta por su Id
    public PurchaseModel getById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow( () -> new PurchaseCustomerNotFoundException(id));
    }

    // Eliminar una venta por id deleteById()
    public void deleteById(Long id) {
        if(purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
        } else {
            throw new PurchaseNotFoundException(id);
        }
    }

    // Actualizar información de venta
    public PurchaseModel updateSale(PurchaseModel purchase, Long id) {
        PurchaseModel onePurchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseCustomerNotFoundException(id));

        onePurchase.setBrand(purchase.getBrand());
        onePurchase.setPhoneModel(purchase.getPhoneModel());
        onePurchase.setPhoneStatus(purchase.getPhoneStatus());
        onePurchase.setDescription(purchase.getDescription());
        onePurchase.setPrice(purchase.getPrice());
        onePurchase.setImageUrl(purchase.getImageUrl());


        return purchaseRepository.save(onePurchase);


    }
}