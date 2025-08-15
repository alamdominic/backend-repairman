package com.repairman.repairman.controller;

import com.repairman.repairman.exceptions.SaleNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.SalesModel;
import com.repairman.repairman.service.CustomerService;
import com.repairman.repairman.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairman")
@CrossOrigin(origins = "*") // habilita las politicas CORS
public class SalesController {
    @Autowired
    private final SalesService salesService;
    private CustomerService customerService; 
    public SalesController(SalesService salesService, CustomerService customerService ) {
        this.salesService = salesService;
        this.customerService = customerService;
    }

    // getSales()
    @GetMapping("/sales")
    public List<SalesModel> getSales() {
        return salesService.getSales();
    }

    // Crear una nueva venta
    @PostMapping("/add-sale")
    public ResponseEntity<SalesModel> addSale(@RequestBody SalesModel newSale) {
        /* 
        SalesModel addNewSale = salesService.addSale(newSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(addNewSale);
        
        */
        //obtiene el ID del cliente del objeto JSON
        Long customerId = newSale.getCustomer().getCustomerID();
        // Busca el CustomerModel en la base de datos
        CustomerModel customer = customerService.findById(customerId);

        if (customer != null) {
            // si el cliente existe, asigna el objeto CustomerModel a la venta
            newSale.setCustomer(customer);
            // Guarda la nueva venta
            SalesModel savedSale = salesService.addSale(newSale);
            return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
        } else {
            // Maneja el caso en que el cliente no se encuentre
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Buscar por id getById()
    @GetMapping("sales/{id}")
    public ResponseEntity<SalesModel> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(salesService.getById(id));
        } catch (SaleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar venta por id deleteById()
    @DeleteMapping("/delete-sale/{id}")
    public ResponseEntity<SalesModel> deleteById(@PathVariable Long id) {
        try {
            salesService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SaleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar una venta updateSale()
    @PutMapping("/update-sale/{id}")
    public ResponseEntity<SalesModel> updateSale(@RequestBody SalesModel sale, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(salesService.updateSale(sale, id));
        } catch (SaleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
