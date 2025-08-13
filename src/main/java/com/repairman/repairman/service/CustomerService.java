package com.repairman.repairman.service;

import com.repairman.repairman.exceptions.CustomerNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.repository.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //constructor de la clase CustomerService y su función es recibir una dependencia (CustomerRepository)
    // y asignarla al campo interno de la clase para poder usarla.
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Metodo para devolver todos los customers
    public List<CustomerModel> getCostumers(){
        return customerRepository.findAll();
    }

    // Método para devolver un único customer una Exception
    public CustomerModel findById(Long id){
        // lanzamos una exception usando lambda orElseThrow
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
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
        return customerRepository.findByUsername(username);
    }

     // Metodo para eliminar por id
    public void deleteById(Long id){
        if (customerRepository.existsById(id)) { // Validando que exista el id
            customerRepository.deleteById(id);
        }else{
            throw new CustomerNotFoundException(id);
        }
    }

    // Metodo para actualizar en base al Id
    // todos los atributos del customer
    public CustomerModel updateCustomer(Long id, CustomerModel customer){
        // map() nos permite hacer una copia que vamos a manipular
        // con el setUsername y luego vamos a almacenar con userRepos...save()
        return customerRepository.findById(id).map(customerMap -> {
            customerMap.setUsername(customer.getUsername());
            customerMap.setFirstname(customer.getFirstname());
            customerMap.setLastname(customer.getLastname());
            customerMap.setEmail(customer.getEmail());
            customerMap.setPassword(customer.getPassword());
            customerMap.setPhonenumber(customer.getPhonenumber());
            return customerRepository.save(customerMap);
        })
        .orElseThrow(() -> new CustomerNotFoundException(id));
        // Lanzamos una exception si el usuario solicitado no existe
    }

}
