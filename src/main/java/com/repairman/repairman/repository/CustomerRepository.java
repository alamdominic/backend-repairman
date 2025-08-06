package com.repairman.repairman.repository;

import com.repairman.repairman.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query("select u from CustomerModel u where u.email = ?1")
    CustomerModel findByEmail(String email);

    @Query("select u from CustomerModel u where u.username = ?1")
    CustomerModel findByUsername(String username);
}


