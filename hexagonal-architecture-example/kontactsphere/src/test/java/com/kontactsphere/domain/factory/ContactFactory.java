package com.kontactsphere.domain.factory;

import com.kontactsphere.domain.model.Contact;

public final class ContactFactory {
    public static Contact createDefaultContact() {
        return new Contact(1L, "John Doe", "john.doe@example.com", "1234567890");
    }

    public static Contact createCustomContact(Long id, String name, String email, String phoneNumber) {
        return new Contact(id, name, email, phoneNumber);
    }

    public static Contact createContactWithName(String name) {
        return new Contact(1L, name, "john.doe@example.com", "1234567890");
    }

    public static Contact createContactWithEmail(String email) {
        return new Contact(1L, "John Doe", email, "1234567890");
    }

    public static Contact createContactWithPhoneNumber(String phoneNumber) {
        return new Contact(1L, "John Doe", "john.doe@example.com", phoneNumber);
    }

    private ContactFactory() {
        throw new AssertionError("Instanciation interdite");
    }
}
