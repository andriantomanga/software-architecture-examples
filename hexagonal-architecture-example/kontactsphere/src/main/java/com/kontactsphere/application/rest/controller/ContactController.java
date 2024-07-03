package com.kontactsphere.application.rest.controller;

import com.kontactsphere.application.rest.mapper.ContactDtoMapper;
import com.kontactsphere.application.rest.model.ContactDto;
import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.api.ReadContactUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contacts")
@AllArgsConstructor
public class ContactController {
    private final CreateContactUseCase createContactUseCase;
    private final ReadContactUseCase readContactUseCase;
    private final ContactDtoMapper contactMapper;

    @PostMapping
    public ResponseEntity<Long> createContact(@RequestBody ContactDto contact) throws BusinessException {
        var createdContactId = createContactUseCase.createContact(contactMapper.toDomainContact(contact));
        return ResponseEntity.ok().body(createdContactId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Long id) throws BusinessException {
        var contact = readContactUseCase.readContactById(id);
        var contactDto = contactMapper.toCompleteContactDto(contact);
        return ResponseEntity.ok().body(contactDto);
    }
}
