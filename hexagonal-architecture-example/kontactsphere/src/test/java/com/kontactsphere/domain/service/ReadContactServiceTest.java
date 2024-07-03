package com.kontactsphere.domain.service;

import com.kontactsphere.domain.exception.ContactNotFoundException;
import com.kontactsphere.domain.factory.ContactFactory;
import com.kontactsphere.domain.port.spi.ReadContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests relatives a la lecture des informations relatives a un contact donne")
class ReadContactServiceTest {
    public static final long SOME_CONTACT_ID = 1L;
    @Mock
    private ReadContactRepository repository;
    @InjectMocks
    private ReadContactService sut;

    @Test
    @DisplayName("Devrait correctement lire un contact par son id")
    void should_correctly_read_contact_by_id() {
        // Given
        var givenContact = ContactFactory.createDefaultContact();
        var givenContactId = givenContact.getId();
        when(repository.readContactById(any())).thenReturn(Optional.of(givenContact));

        // When
        var actualContact = sut.readContactById(givenContactId);

        // Then
        assertThat(actualContact)
                .usingRecursiveComparison()
                .isEqualTo(givenContact);
    }

    @Test
    @DisplayName("Devrait declencher une exception lorsque le contact specifie n'existe pas")
    void should_throw_ContactNotFoundException_when_contact_is_not_found() {
        // Given
        when(repository.readContactById(any())).thenReturn(Optional.empty());

        // When + Then
        var exception = assertThrows(ContactNotFoundException.class, () -> sut.readContactById(SOME_CONTACT_ID));
        var expectedExceptionMessage = "Contact d'id %d introuvable".formatted(SOME_CONTACT_ID);
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
}