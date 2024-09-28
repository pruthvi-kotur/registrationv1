package com.api1.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String msg)  {
        super(msg);
    }
}
