package com.repairman.repairman.service;

import com.repairman.repairman.exceptions.CustomerNotFoundException;
import com.repairman.repairman.exceptions.SaleNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.SalesModel;
import com.repairman.repairman.repository.CustomerRepository;
import com.repairman.repairman.repository.SalesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    @Autowired
    private final SalesRepository salesRepository;
    private final CustomerRepository customerRepository; // Se inyecta para hacer validaciones

    public SalesService(SalesRepository salesRepository, CustomerRepository customerRepository) {
        this.salesRepository = salesRepository;
        this.customerRepository = customerRepository;
    }

    // Método para recuperar todas las sales
    public List<SalesModel> getSales() {
        return salesRepository.findAll();
    }

    // Método para crear una nueva venta
    public SalesModel addSale(SalesModel newSale) {
        CustomerModel customerSale = newSale.getCustomer();

        // Busca al Customer en la base de datos usando su ID
        CustomerModel existingCustomer = customerRepository.findById(customerSale.getCustomerID())
        .orElseThrow(() -> new CustomerNotFoundException(customerSale.getCustomerID())); // <-- Maneja el caso si el cliente no existe

        newSale.setCustomer(existingCustomer); // Asocia el customerID con la una nueva Sale
        return salesRepository.save(newSale);
    }

    // Recuperar una venta por su Id
    public SalesModel getById(Long id) {
        return salesRepository.findById(id).orElseThrow( () -> new SaleNotFoundException(id));

    }

    // Eliminar una venta por id deleteById()
    public void deleteById(Long id) {
        if(salesRepository.existsById(id)) {
            salesRepository.deleteById(id);
        } else {
            throw new SaleNotFoundException(id);
        }
    }

    // Actualizar información de venta
    public SalesModel updateSale(SalesModel sale, Long id) {
        SalesModel oneSale = salesRepository.findById(id)
            .orElseThrow(() -> new SaleNotFoundException(id));
        
        oneSale.setBrand(sale.getBrand());
        oneSale.setCellphoneStatus(sale.getCellphoneStatus());
        oneSale.setCreatedAt(sale.getCreatedAt());
        oneSale.setDescription(sale.getDescription());
        oneSale.setModel(sale.getModel());
        oneSale.setPrice(sale.getPrice());
        
        return salesRepository.save(oneSale);
        /*
        return salesRepository.findById(id).map(salesModel -> {
            // Actualizar los campos con los valores del parámetro 'sale'
            salesModel.setBrand(sale.getBrand());
            salesModel.setCellphoneStatus(sale.getCellphoneStatus());
            salesModel.setCreatedAt(salesModel.getCreatedAt());
            salesModel.setDescription(salesModel.getDescription());
            salesModel.setModel(salesModel.getModel());
            salesModel.setPrice(salesModel.getPrice());
            }).orElseThrow( () -> new SaleNotFoundException(id));
        */
    }
}
