package com.kontactsphere.domain.service;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class CreateContactService implements CreateContactUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContactService.class);
    private final CreateContactRepository createContactRepository;

    @Override
    public Long createContact(Contact contact) throws BusinessException {
        LOGGER.info("Creation d'un nouveau contact en cours ...");
        contact.validate();
        return createContactRepository.createContact(contact);
    }
}
