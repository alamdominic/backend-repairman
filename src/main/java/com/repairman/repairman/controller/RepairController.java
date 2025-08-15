package com.repairman.repairman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repairman.repairman.exceptions.RepairNotFoundException;
import com.repairman.repairman.model.CustomerModel;
import com.repairman.repairman.model.RepairModel;
import com.repairman.repairman.service.CustomerService;
import com.repairman.repairman.service.RepairService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/repairman")
@CrossOrigin(origins = "*") // habilita las politicas CORS
public class RepairController {
    @Autowired
    private final RepairService repairService;
    private final CustomerService customerService;

    public RepairController(RepairService repairService, CustomerService customerService) {
        this.repairService = repairService;
        this.customerService = customerService;
    }

    // GET repairs
    @GetMapping("/repeairs")
    public List<RepairModel> gatAll() {
        return repairService.getRepairs();
    }

    // POST add-repair
    @PostMapping("/add-repair")
    public ResponseEntity<RepairModel> addRepair(@RequestBody RepairModel newRepair) {
        
        Long customerId = newRepair.getCustomer().getCustomerID();

        // Busca el CustomerModel en la base de datos
        CustomerModel customer = customerService.findById(customerId);

        // valida customer
        if (customer != null){
            newRepair.setCustomer(customer);

            RepairModel nuevaReparacion = repairService.newRepair(newRepair);

            return new ResponseEntity<>(nuevaReparacion, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    // Get repairById
    @GetMapping("/repair/{id}")
    public ResponseEntity<RepairModel> getRepairById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(repairService.getById(id));
        }catch (RepairNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE deleteRepairById
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RepairModel> deleteRepairById(@PathVariable Long id){
        try{
            repairService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (RepairNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    // PUT updateRepairById
    @PutMapping("/update-repair/{id}")
    public ResponseEntity<RepairModel> updateRepairById(@RequestBody RepairModel repair, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(repairService.updateRepair(repair, id));
        } catch (RepairNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

        

    
}
