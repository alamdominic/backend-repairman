package com.repairman.repairman.repository;

import com.repairman.repairman.model.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {
    // Para buscar UNA compra específica por ID único
    //Optional: Manejo seguro de valores nulos & Buena práctica de Spring Data JPA
    //Spring Data JPA recomienda usar Optional cuando el resultado puede no existir.
    @Query("select u from PurchaseModel u where u.purchaseID = ?1")
    Optional<PurchaseModel> findByPurchaseID(Long purchaseID);

    // Para buscar TODAS las compras de un customer
    @Query("SELECT p FROM PurchaseModel p WHERE p.customer.ID = :customerId")
    List<PurchaseModel> findByCustomerId(@Param("customerId") Long customerId);

    // Para buscar TODAS las compras de una marca
    @Query("select u from PurchaseModel u where u.brand = ?1")
    List<PurchaseModel> findByBrand(String brand);
}
