package com.health.pramod.HealthCare.exception;

public class EmailSendingException extends RuntimeException{
    public EmailSendingException(String message){
        super(message);
    }
}
