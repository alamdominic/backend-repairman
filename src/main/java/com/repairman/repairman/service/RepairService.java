package com.repairman.repairman.service;

import com.repairman.repairman.model.RepairModel;
import com.repairman.repairman.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private final RepairRepository repairRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<RepairModel> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Optional<RepairModel> getRepairById(Long id) {
        return repairRepository.findById(id);
    }

    public RepairModel createRepair(RepairModel repair) {
        return repairRepository.save(repair);
    }

    public RepairModel updateRepair(Long id, RepairModel repairDetails) {
        Optional<RepairModel> existingRepairOpt = repairRepository.findById(id);

        if (existingRepairOpt.isPresent()) {
            RepairModel existingRepair = existingRepairOpt.get();
            existingRepair.setCustumerid(repairDetails.getCustumerid());
            existingRepair.setBrand(repairDetails.getBrand());
            existingRepair.setIssue(repairDetails.getIssue());
            existingRepair.setDescription(repairDetails.getDescription());
            existingRepair.setPrice(repairDetails.getPrice());
            existingRepair.setCreatedat(repairDetails.getCreatedat());
            existingRepair.setCustomer(repairDetails.getCustomer());
            return repairRepository.save(existingRepair);
        } else {
            return null; // O podrías lanzar una excepción
        }
    }

    public boolean deleteRepair(Long id) {
        if (repairRepository.existsById(id)) {
            repairRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
