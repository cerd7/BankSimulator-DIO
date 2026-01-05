package org.cerd.bank.exception;

import java.io.IOException;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String message, IOException e) {
        super(message,e);
    }
}
