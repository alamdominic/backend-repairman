package com.repairman.repairman.exceptions;

public class RepairNotFoundException extends RuntimeException{
    public RepairNotFoundException(Long id){
        super("No se encontro la repacion con " + id);
    }
}
