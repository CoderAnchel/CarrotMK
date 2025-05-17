package org.lince.pulsarbrokerjvm.Exceptions;

public class ConsumerNotFoundException extends RuntimeException {
    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
