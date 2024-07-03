package com.kontactsphere.domain.exception;

public class ContactNotFoundException extends BusinessException {
    public ContactNotFoundException(Long contactId) {
        super("Contact d'id %d introuvable".formatted(contactId));
    }

    public ContactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactNotFoundException(String message) {
        super(message);
    }
}
