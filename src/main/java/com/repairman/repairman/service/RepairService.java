package com.repairman.repairman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairman.repairman.exceptions.CustomerNotFoundException;
import com.repairman.repairman.exceptions.RepairNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.RepairModel;
import com.repairman.repairman.repository.CustomerRepository;
import com.repairman.repairman.repository.RepairRepository;

@Service
public class RepairService {
    @Autowired
    private final RepairRepository repairRepository;
    private final CustomerRepository customerRepository;

    public RepairService(RepairRepository repairRepository, CustomerRepository customerRepository) {
        this.repairRepository = repairRepository;
        this.customerRepository = customerRepository;
    }

    // Método para recuperar todas las repaciones
    public List<RepairModel> getRepairs() {
        return repairRepository.findAll();
    }

    // Recuperar una reparacion por su Id
    public RepairModel getById(Long id) {
        return repairRepository.findById(id)
        .orElseThrow( () -> new RepairNotFoundException(id));
    }

    // Agrega una reparacion
    public RepairModel newRepair(RepairModel newRepair){
        CustomerModel customerRepair = newRepair.getCustomer();

        CustomerModel existingCustomer = customerRepository.findById(customerRepair.getCustomerID())
        .orElseThrow(() -> new CustomerNotFoundException(customerRepair.getCustomerID())); // <-- Maneja el caso si el cliente no existe

        newRepair.setCustomer(existingCustomer); // Asocia el customerID con la una nueva Sale
        return repairRepository.save(newRepair);
    }
    
    // Eliminar una reparacion por id
    public void deleteById(Long id){
        if (repairRepository.existsById(id)) {
            repairRepository.deleteById(id);
        }else{
            throw new RepairNotFoundException(id);
        }
    }

    // Actualizar repacion por id
    public RepairModel updateRepair(RepairModel repair, Long id){
        RepairModel onRepair = repairRepository.findById(id)
        .orElseThrow(() -> new RepairNotFoundException(id));

        onRepair.setBrand(repair.getBrand());
        onRepair.setCreatedat(repair.getCreatedat());
        onRepair.setDescription(repair.getDescription());
        onRepair.setIssue(repair.getIssue());
        onRepair.setModel(repair.getModel());
        onRepair.setPrice(repair.getPrice());
        onRepair.setImageUrl(repair.getImageUrl());

        return repairRepository.save(onRepair);
    }
}
