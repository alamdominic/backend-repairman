package com.repairman.repairman.controller;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Método para controlar la inserción de un nuevo usuario
@RestController
@RequestMapping("/api/v1/repairman")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // Mapear getUsers() from UserService
    @GetMapping("/customers")
    public List<CustomerModel> getCostumers(){
        return customerService.getCostumers();
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
}