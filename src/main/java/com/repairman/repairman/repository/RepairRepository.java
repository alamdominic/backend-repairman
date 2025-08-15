package com.repairman.repairman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.repairman.repairman.model.RepairModel;

@Repository
public interface RepairRepository extends JpaRepository<RepairModel, Long> {
    @Query("SELECT s FROM RepairModel s WHERE s.repairID = :id ")
    RepairModel findRepairById(Long id);
}
