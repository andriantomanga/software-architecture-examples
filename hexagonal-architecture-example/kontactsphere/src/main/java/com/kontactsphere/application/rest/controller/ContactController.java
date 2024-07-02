package com.kontactsphere.application.rest.controller;

import com.kontactsphere.application.rest.mapper.ContactDtoMapper;
import com.kontactsphere.application.rest.model.ContactDto;
import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contacts")
@AllArgsConstructor
public class ContactController {
    private final CreateContactUseCase createContactUseCase;
    private final ContactDtoMapper contactMapper;

    @PostMapping
    public ResponseEntity<Long> createContact(@RequestBody ContactDto contact) throws BusinessException {
        var createdContactId = createContactUseCase.createContact(contactMapper.toDomainContact(contact));
        return ResponseEntity.ok().body(createdContactId);
    }
}
