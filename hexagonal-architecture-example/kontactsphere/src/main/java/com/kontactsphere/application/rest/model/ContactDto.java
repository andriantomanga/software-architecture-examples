package com.kontactsphere.application.rest.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Getter
@Jacksonized
public class ContactDto {
    private final String name;
    private final String email;
    private final String phoneNumber;
}
