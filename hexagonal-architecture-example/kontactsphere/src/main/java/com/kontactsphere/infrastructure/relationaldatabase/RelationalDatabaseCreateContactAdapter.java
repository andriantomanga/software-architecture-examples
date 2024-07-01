package com.kontactsphere.infrastructure.relationaldatabase;

import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import com.kontactsphere.infrastructure.relationaldatabase.mapper.DbContactMapper;
import com.kontactsphere.infrastructure.relationaldatabase.repository.DbContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RelationalDatabaseCreateContactAdapter implements CreateContactRepository {
    private final DbContactRepository contactRepository;
    private final DbContactMapper contactMapper;

    @Override
    public Long createContact(Contact contact) {
        var createdContact = contactRepository.save(contactMapper.toDbContact(contact));
        return createdContact.getId();
    }
}
