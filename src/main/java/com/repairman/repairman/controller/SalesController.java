package com.repairman.repairman.controller;

import com.repairman.repairman.exceptions.SaleNotFoundException;
import com.repairman.repairman.model.SalesModel;
import com.repairman.repairman.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairman")
public class SalesController {
    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // getSales()
    @GetMapping("/sales")
    public List<SalesModel> getSales() {
        return salesService.getSales();
    }

    // Crear una nueva venta
    @PostMapping("add-sale")
    public SalesModel addSale(@RequestBody SalesModel newSale) {
        return salesService.addSale(newSale);
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

    // Actaulizar una venta updateSale()
    @PutMapping("/update-sale/{id}")
    public ResponseEntity<SalesModel> updateSale(@RequestBody SalesModel sale, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(salesService.updateSale(sale, id));
        } catch (SaleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
