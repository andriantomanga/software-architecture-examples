package com.kontactsphere.domain.service;

import com.kontactsphere.domain.annotation.UseCase;
import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.exception.ContactNotFoundException;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.api.ReadContactUseCase;
import com.kontactsphere.domain.port.spi.ReadContactRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class ReadContactService implements ReadContactUseCase {
    private final ReadContactRepository repository;

    @Override
    public Contact readContactById(Long contactId) throws BusinessException {
        return repository.readContactById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(contactId));
    }
}
