package com.kontactsphere.application.rest.mapper;

import com.kontactsphere.application.rest.model.ContactDto;
import com.kontactsphere.domain.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactDtoMapper {
    Contact toDomainContact(ContactDto contact);
}
