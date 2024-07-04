package com.kontactsphere.application.rest.controller;

import com.kontactsphere.application.rest.mapper.ContactDtoMapper;
import com.kontactsphere.application.rest.model.ContactDto;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.api.ReadContactUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {
    @Mock
    private CreateContactUseCase createContactUseCase;
    @Mock
    private ReadContactUseCase readContactUseCase;
    @Mock
    private ContactDtoMapper contactMapper;

    @InjectMocks
    private ContactController sut;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    void should_correctly_create_new_contact_and_return_id() throws Exception {
        // Given
        var contact = Contact.builder().name("John Doe").email("john.doe@example.com").build();
        var expectedId = 2L;

        when(contactMapper.toDomainContact(any(ContactDto.class))).thenReturn(contact);
        when(createContactUseCase.createContact(any(Contact.class))).thenReturn(expectedId);

        // When + Then
        mockMvc.perform(post("/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void should_correctly_read_contact_by_id() throws Exception {
        // Given
        var contact = Contact.builder().name("Patrick").email("patrick@gmail.com").build();
        var contactDto = ContactDto.builder().name("Patrick").email("patrick@gmail.com").build();

        when(readContactUseCase.readContactById(any())).thenReturn(contact);
        when(contactMapper.toCompleteContactDto(any())).thenReturn(contactDto);

        // When + Then
        mockMvc.perform(get("/v1/contacts/%d".formatted(1L)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Patrick\", \"email\":\"patrick@gmail.com\"}"));
    }
}