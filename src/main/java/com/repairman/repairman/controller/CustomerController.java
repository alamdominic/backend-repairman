package com.repairman.repairman.controller;

import com.repairman.repairman.exceptions.LogInException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.service.CustomerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.repairman.repairman.exceptions.CustomerNotFoundException;

import javax.security.auth.login.LoginException;


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
        // Despues de evaluar si no existe hace la insercción del registro
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerModel loginData) {
    /*
    {JSON
        "email": "juan@email.com",
        "password": "123456"
    }
    */
        try {
                // TODO: extraer email y password del CustomerModel recibido
            String frontEmail = loginData.getEmail();
            String frontPassword = loginData.getPassword();
                // TODO: llamar al método login del service pasando email y password
            CustomerModel resultLogIn = customerService.login(frontEmail, frontPassword);
                // TODO: si llega aquí, el login fue exitoso
                // TODO: retornar ResponseEntity.ok() con el customer encontrado o mensaje de éxito
            return ResponseEntity.ok(resultLogIn);
        } catch (LogInException e) {
                // TODO: manejar error de login
                // TODO: retornar ResponseEntity.badRequest() o status 401 con mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // TODO: manejar cualquier otro error inesperado retornar ResponseEntity con status 500
            //Es igual que: return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
}
