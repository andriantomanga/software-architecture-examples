package com.kontactsphere.domain.model;

import com.kontactsphere.domain.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactTest {
    private static final String GIVEN_EMAIL_ADDRESS = "obiwab.kenobi@kontactsphere.com";
    private static final String GIVEN_PHONE_NUMBER = "060578895489";
    private static final String GIVEN_CONTACT_NAME = "Obi-Wan Kenobi";
    private static final String MISSING_NAME_MSG = "Le nom est obligatoire !";
    private static final String MISSING_CONTACT_MSG = "Au moins une information de contact est requise !";

    @ParameterizedTest(name = "[Nom: {0}, email: {1}, Tel: {2}] => Message attendu: {3}")
    @MethodSource("invalidContactArgumentProvider")
    @DisplayName("Devrait declencer une erreur lors de la validation du contact")
    void should_correctly_validate_contact(String givenContactName, String givenEmailAddress, String givenPhoneNumber, String expectedMessage) {
        // Given
        var givenContact = Contact.builder()
                .name(givenContactName)
                .email(givenEmailAddress)
                .phoneNumber(givenPhoneNumber)
                .build();

        // When + Then
        var exception = assertThrows(BusinessException.class, givenContact::validate);
        assertEquals(expectedMessage, exception.getMessage());
    }

    static Stream<Arguments> invalidContactArgumentProvider() {
        return Stream.of(
                Arguments.of(null, GIVEN_EMAIL_ADDRESS, GIVEN_PHONE_NUMBER, MISSING_NAME_MSG),
                Arguments.of(GIVEN_CONTACT_NAME, null, null, MISSING_CONTACT_MSG));
    }
}