package com.kontactsphere.infrastructure.relationaldatabase;

import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.spi.ReadContactRepository;
import com.kontactsphere.infrastructure.relationaldatabase.mapper.DbContactMapper;
import com.kontactsphere.infrastructure.relationaldatabase.repository.DbContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RelationalDatabaseReadContactAdapter implements ReadContactRepository {
    private final DbContactRepository contactRepository;
    private final DbContactMapper contactMapper;

    @Override
    public Optional<Contact> readContactById(Long contactId) {
        return contactRepository.findById(contactId).map(contactMapper::toDomainContact);
    }
}
