package com.repairman.repairman.controller;

import com.repairman.repairman.model.RepairModel;
import com.repairman.repairman.service.RepairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping
    public List<RepairModel> getAllRepairs() {
        return repairService.getAllRepairs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairModel> getRepairById(@PathVariable Long id) {
        return repairService.getRepairById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RepairModel> createRepair(@Valid @RequestBody RepairModel repair) {
        RepairModel savedRepair = repairService.createRepair(repair);
        return ResponseEntity.ok(savedRepair);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairModel> updateRepair(
            @PathVariable Long id,
            @Valid @RequestBody RepairModel repairDetails) {
        RepairModel updatedRepair = repairService.updateRepair(id, repairDetails);
        if (updatedRepair == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRepair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        boolean deleted = repairService.deleteRepair(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
