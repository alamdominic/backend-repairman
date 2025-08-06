package com.repairman.repairman.controller;
import com.repairman.repairman.model.CustomerModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//Método para controlar la inserción de un nuevo usuario
@RestController
@RequestMapping("/api/v1/repairman")
public class CustomerController {

    @PostMapping ("/create-customer")
    public String addNewCustomer(@RequestBody CustomerModel n){

    }

    /*
    @PostMapping("/create-costumer")
 public User addCostumer(@RequestBody Costumer newCostumer) {
return costumerService.createCostumer(newCostumer)*/
}
