package org.cerd.bank.exception;

public class InvalidCpfException extends RuntimeException
{
    public InvalidCpfException(String message)
    {
        super(message);
    }
}
