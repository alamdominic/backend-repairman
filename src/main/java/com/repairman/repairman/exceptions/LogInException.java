package com.repairman.repairman.exceptions;

public class LogInException extends Exception{
    public LogInException (String email){

        super("No se encontró el correo: " + email);
    }
}
