package com.kontactsphere.application.rest.controller;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;
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

    @PostMapping
    public ResponseEntity<Long> createContact(@RequestBody Contact contact) throws BusinessException {
        var createdContactId = createContactUseCase.createContact(contact);
        return ResponseEntity.ok().body(createdContactId);
    }
}
