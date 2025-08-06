package com.repairman.repairman.service;

import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;}

    public CustomerService createUser(CustomerModel newUser){
        return CustomerRepository.save(newUser);
    }

}
