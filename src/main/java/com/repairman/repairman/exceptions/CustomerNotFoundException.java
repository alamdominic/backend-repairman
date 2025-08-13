package com.repairman.repairman.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){

        super("No se encontró el cliente con id" + id);
    }
}
