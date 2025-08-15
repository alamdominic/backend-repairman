package com.repairman.repairman.repository;

import com.repairman.repairman.model.PurchaseModel;
import com.repairman.repairman.model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {
    // findBySalesID le dice a JPA que una búsque por la propiedad salesID de la entidad
    // Spring Data JPA crea automáticamente estos métodos:
    // - save()
    // - findById()
    // - findAll()
    // - deleteById()
    // - existsById()
    @Query("SELECT s FROM SalesModel s WHERE s.salesID = :id ")
    PurchaseModel findPurchaseById(Long id);
}
