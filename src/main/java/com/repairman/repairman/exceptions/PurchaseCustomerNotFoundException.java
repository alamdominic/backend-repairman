package com.repairman.repairman.exceptions;

//Nombre de la clase y nombre de la excepción deben de coincidir para no devolver nada
public class PurchaseCustomerNotFoundException extends RuntimeException {

    public PurchaseCustomerNotFoundException(Long customerID) {
        super("No se encontraron compras para el cliente con ID: " + customerID);
    }
}