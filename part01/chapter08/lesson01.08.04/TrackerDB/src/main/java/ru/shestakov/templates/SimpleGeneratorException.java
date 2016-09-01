package ru.shestakov.templates;

public class SimpleGeneratorException extends Exception{

    private String message;
    public String getMessage(){return message;}

    public SimpleGeneratorException(String mes){
        message=mes;
    }

    public SimpleGeneratorException(String mes, Throwable cause){
        super(cause);
        message=mes;
    }

}