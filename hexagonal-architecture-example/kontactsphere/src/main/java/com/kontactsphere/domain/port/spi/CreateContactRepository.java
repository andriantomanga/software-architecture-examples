package com.kontactsphere.domain.port.spi;

import com.kontactsphere.domain.model.Contact;

public interface CreateContactRepository {
    Long createContact(Contact contact);
}
