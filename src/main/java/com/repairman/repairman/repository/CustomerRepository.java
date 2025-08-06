package com.repairman.repairman.repository;

import com.repairman.repairman.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query("SELECT u FROM customers u WHERE u.email = ?1")
    CustomerModel findByEmail(String email);

    @Query("SELECT u FROM customers u WHERE u.username = ?1") // Usa el nombre exacto del campo en User
    CustomerModel findByUserName(String username);
}
