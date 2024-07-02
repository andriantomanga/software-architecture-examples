package com.kontactsphere.application.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Getter
@Jacksonized
//@JsonDeserialize(builder = ContactDto.ContactDtoBuilder.class)
public class ContactDto {
    private  String name;
    private  String email;
    private  String phoneNumber;

//    @JsonPOJOBuilder(withPrefix="")
//    public static class ContactDtoBuilder {}
}
