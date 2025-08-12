package com.repairman.repairman.repository;

import com.repairman.repairman.model.SalesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Long> {
    // Devuelve el objeto directamente, si el registro no se encuentra retorna null.
    SalesModel findBySalesID(Long salesID);
    // findBySalesID le dice a JPA que una búsque por la propiedad salesID de la entidad



}
