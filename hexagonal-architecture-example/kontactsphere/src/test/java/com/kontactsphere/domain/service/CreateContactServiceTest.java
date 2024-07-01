package com.kontactsphere.domain.service;

import com.kontactsphere.domain.exception.BusinessException;
import com.kontactsphere.domain.model.Contact;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests relatives a la creation d'un nouveau contact")
class CreateContactServiceTest {
    private static final String GIVEN_EMAIL_ADDRESS = "obiwab.kenobi@kontactsphere.com";
    private static final String GIVEN_PHONE_NUMBER = "060578895489";
    private static final String GIVEN_CONTACT_NAME = "Obi-Wan Kenobi";
    private static final String MISSING_NAME_MSG = "Le nom est obligatoire !";
    private static final String MISSING_CONTACT_MSG = "Au moins une information de contact est requise !";
    private static final long GIVEN_CONTACT_ID = 1L;

    @Mock
    private CreateContactRepository repository;
    @InjectMocks
    private CreateContactService sut;

    @Test
    @DisplayName("Devrait creer correctement un nouveau contact")
    void should_successfully_create_contact() {
        // Given
        when(repository.createContact(any())).thenReturn(GIVEN_CONTACT_ID);
        var givenContact = new Contact(null, GIVEN_CONTACT_NAME, GIVEN_EMAIL_ADDRESS, GIVEN_PHONE_NUMBER);

        // When
        var actualContactId = sut.createContact(givenContact);

        // Then
        assertAll(
                () -> verify(repository).createContact(any()),
                () -> assertThat(actualContactId).isEqualTo(GIVEN_CONTACT_ID)
        );
    }

    @ParameterizedTest(name = "[Nom: {0}, email: {1}, Tel: {1}] => Message attendu: '{2}'")
    @MethodSource("invalidContactArgumentProvider")
    @DisplayName("Devrait declencer une erreur et ne pas creer un nouveau contact")
    void should_throw_business_exception_when_contact_is_invalid(String givenContactName, String givenEmailAddress, String givenPhoneNumber, String expectedMessage) {
        // Given
        var givenContact = new Contact(null, givenContactName, givenEmailAddress, givenPhoneNumber);

        // When+Then
        var exception = assertThrows(BusinessException.class, () -> sut.createContact(givenContact));

        assertAll(
                () -> assertEquals(expectedMessage, exception.getMessage()),
                () -> verify(repository, never()).createContact(any())
        );
    }

    static Stream<Arguments> invalidContactArgumentProvider() {
        return Stream.of(
                Arguments.of(null, GIVEN_EMAIL_ADDRESS, GIVEN_PHONE_NUMBER, MISSING_NAME_MSG),
                Arguments.of(GIVEN_CONTACT_NAME, null, null, MISSING_CONTACT_MSG));
    }
}