package com.repairman.repairman.controller;
import com.repairman.repairman.exceptions.PurchaseCustomerNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.PurchaseModel;
import com.repairman.repairman.model.SalesModel;
import com.repairman.repairman.service.CustomerService;
import com.repairman.repairman.service.PurchaseService;
import com.repairman.repairman.service.SalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/repairman/purchases-controller")
@CrossOrigin(origins = "*") // habilita las politicas CORS
public class PurchaseController {
    private final PurchaseService purchaseService;
    private CustomerService customerService;

    public PurchaseController(PurchaseService purchaseService, CustomerService customerService) {
        this.purchaseService = purchaseService;
        this.customerService = customerService;
    }

    // Obtener TODAS las compras
    @GetMapping ("/purchases")
    public List<PurchaseModel> getPurchases() {
        return purchaseService.getPurchases();
    }

    //Crear nueva compra
    @PostMapping("/add-purchase")
    public ResponseEntity<PurchaseModel> addPurchase(@RequestBody PurchaseModel newPurchase) {
        //obtiene el ID del cliente del objeto JSON
        Long customerId = newPurchase.getCustomer().getCustomerID();
        // Busca el CustomerModel en la base de datos
        CustomerModel customer = customerService.findById(customerId);

        if (customer != null) {
            // si el cliente existe, asigna el objeto CustomerModel a la venta
            newPurchase.setCustomer(customer);
            // Guarda la nueva venta
            PurchaseModel savedPurchase = purchaseService.addPurchase(newPurchase);
            return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
        } else {
            // Maneja el caso en que el cliente no se encuentre
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Buscar por id getById()
    @GetMapping("purchases/{id}")
    public ResponseEntity<PurchaseModel> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(purchaseService.getById(id));
        } catch (PurchaseCustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar compra por id deleteById()
    @DeleteMapping("/delete-purchase/{id}")
    public ResponseEntity<SalesModel> deleteById(@PathVariable Long id) {
        try {
            purchaseService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (PurchaseCustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar una compra updateSale()
    @PutMapping("/update-sale/{id}")
    public ResponseEntity<PurchaseModel> updateSale(@RequestBody PurchaseModel sale, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.updateSale(sale, id));
        } catch (PurchaseCustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}