package com.repairman.repairman.repository;

import com.repairman.repairman.model.RepairModel;
import com.repairman.repairman.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairModel, Long> {

    // Buscar todas las reparaciones de un cliente por email (relación con Customer)
    @Query("SELECT r FROM RepairModel r WHERE r.customer.email = ?1")
    List<RepairModel> findByCustomerEmail(String email);

    // Buscar todas las reparaciones de un cliente por username
    @Query("SELECT r FROM RepairModel r WHERE r.customer.username = ?1")
    List<RepairModel> findByCustomerUsername(String username);

    // Buscar reparaciones por marca (brand)
    @Query("SELECT r FROM RepairModel r WHERE r.brand = ?1")
    List<RepairModel> findByBrand(String brand);

    // Buscar reparaciones que contengan una palabra en la descripción (ignore case)
    @Query("SELECT r FROM RepairModel r WHERE LOWER(r.description) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<RepairModel> findByDescriptionContaining(String keyword);

    // Buscar reparaciones por precio mayor o igual
    @Query("SELECT r FROM RepairModel r WHERE r.price >= ?1")
    List<RepairModel> findByPriceGreaterThanEqual(Double price);

    // Métodos derivados (sin @Query) que Spring genera automáticamente basado en el nombre
    List<RepairModel> findByIssue(String issue);

}
