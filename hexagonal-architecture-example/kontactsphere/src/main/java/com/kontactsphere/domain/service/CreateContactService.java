package com.kontactsphere.domain.service;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateContactService implements CreateContactUseCase {
    private final CreateContactRepository createContactRepository;

    @Override
    public Long createContact(Contact contact) throws BusinessException {
        contact.validate();
        return createContactRepository.createContact(contact);
    }
}
