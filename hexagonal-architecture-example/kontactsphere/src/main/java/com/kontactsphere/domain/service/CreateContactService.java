package com.kontactsphere.domain.service;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class CreateContactService implements CreateContactUseCase {
    private final CreateContactRepository createContactRepository;

    @Override
    public Long createContact(Contact contact) throws BusinessException {
        validateContact(contact);
        return createContactRepository.createContact(contact);
    }

    private void validateContact(Contact contact) throws BusinessException {
        if (StringUtils.isBlank(contact.name())) {
            throw new BusinessException("Le nom est obligatoire !");
        }
        if (StringUtils.isAllBlank(contact.email(), contact.phoneNumber())) {
            throw new BusinessException("Au moins une information de contact est requise !");
        }
    }
}
