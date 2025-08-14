package com.repairman.repairman.controller;

import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.service.CustomerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.repairman.repairman.exceptions.CustomerNotFoundException;


//Método para controlar la inserción de un nuevo usuario
@RestController
@RequestMapping("/api/v1/repairman")
@CrossOrigin(origins = "*") // habilita las politicas CORS
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Mapear getCustomers() from UserService
    @GetMapping("/customers")
    public List<CustomerModel> getCostumers(){
        return customerService.getCostumers();
    }

    // Mapear findById
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerModel> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(customerService.findById(id));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mapear create Customer usando ResponseEntity
    @PostMapping("/create-customer")
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody CustomerModel newCustomer){
        CustomerModel customerEmail = customerService.findByEmail(newCustomer.getEmail());
        CustomerModel customerName = customerService.findByUsername(newCustomer.getUsername());
        // Evaluar si customer existe por email o username
        if(customerEmail != null || customerName != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Error 409
        }
        // Despues de evaluar si no existe hace la inserccion del registro
        CustomerModel addNewCustomerResponse = customerService.createCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(addNewCustomerResponse);
    }
    
    // Metodo para eliminar customer
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<CustomerModel> deleteById(@PathVariable Long id){
        try {
            customerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mapear el metodo updateCustomer
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<CustomerModel> updateCostumer(@PathVariable Long id, @RequestBody CustomerModel customer){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(id, customer));
        } catch (CustomerNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/demo")
    public String dashboard() {
        return "Endpoint asegurado";
    }
    
}
