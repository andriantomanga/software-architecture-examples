package com.kontactsphere.domain.port.api;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;

public interface CreateContactUseCase {
    Long createContact(Contact contact) throws BusinessException;
}
