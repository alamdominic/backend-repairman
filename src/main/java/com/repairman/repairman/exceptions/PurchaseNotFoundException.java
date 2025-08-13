package com.repairman.repairman.exceptions;

public class PurchaseNotFoundException extends RuntimeException{
    //Manejo de excepción en caso de que no encontrar la compra por ID
    public PurchaseNotFoundException(Long purchaseID){
        super("No se encontró el la compra con id" + purchaseID);
    }


}
