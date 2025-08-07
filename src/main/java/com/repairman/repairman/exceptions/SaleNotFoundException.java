package com.repairman.repairman.exceptions;

public class SaleNotFoundException extends RuntimeException{
    public SaleNotFoundException(Long id){
        super("No se encontro la venta con id" + id);
    }
}
