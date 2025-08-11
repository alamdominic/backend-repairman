package com.repairman.repairman.service;

import com.repairman.repairman.exceptions.SaleNotFoundException;
import com.repairman.repairman.model.SalesModel;
import com.repairman.repairman.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    // Método para recuperar todas las sales
    public List<SalesModel> getSales() {
        return salesRepository.findAll();
    }

    // Método para crear un nuevo usuario
    public SalesModel addSale(SalesModel newSale) {
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

    // Actualizar informaciín de venta
    public SalesModel updateSale(SalesModel sale, Long id) {
        return salesRepository.findById(id).map(salesModel -> {
            salesModel.setBrand(salesModel.getBrand());
            salesModel.setCellphoneStatus(sale.getCellphoneStatus());
            salesModel.setCreatedAt(salesModel.getCreatedAt());
            salesModel.setDescription(salesModel.getDescription());
            salesModel.setModel(salesModel.getModel());
            salesModel.setPrice(salesModel.getPrice());
        }).orElseThrow( () -> new SaleNotFoundException(id));
    }
}
