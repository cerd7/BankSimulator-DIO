package org.cerd.bank.exception;

public class InvalidCpf extends Exception
{
    public InvalidCpf(String verifyCpf)
    {
        super(verifyCpf);
    }
}
