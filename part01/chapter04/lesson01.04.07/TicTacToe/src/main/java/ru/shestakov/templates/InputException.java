package ru.shestakov.templates;

public class InputException extends Exception{

    private String message;
    public String getMessage(){return message;}

    public InputException(String mes){
        message=mes;
    }

    public InputException(String mes, Throwable cause){
        super(cause);
        message=mes;
    }

}
