package com.repairman.repairman.service;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
     private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Método para devolver todos los customers
    public List<CustomerModel> getCostumers(){
        return customerRepository.findAll();
    }

   // Metodo para crear nuevo Customer
    public CustomerModel createCustomer(CustomerModel newCustomer){
        return customerRepository.save(newCustomer);
    }
    // Metodo para recuperar a un user por email
    public CustomerModel findByEmail(String email){
        return customerRepository.findByEmail(email);
    }
    // Metodo para recuperar un User por username
    public CustomerModel findByUsername(String username){
        return customerRepository.findByUserName(username);
    }
}
