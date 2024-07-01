package com.kontactsphere.infrastructure.relationaldatabase.mapper;

import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.infrastructure.relationaldatabase.model.DbContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DbContactMapper {
    Contact toDomainContact(DbContact contactEntity);

    DbContact toDbContact(Contact contact);
}
