package com.kontactsphere.domain.port.spi;

import com.kontactsphere.domain.model.Contact;

import java.util.Optional;

public interface ReadContactRepository {
    Optional<Contact> readContactById(Long contactId);
}
