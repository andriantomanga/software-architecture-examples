package com.kontactsphere.domain.model;

import lombok.Builder;

@Builder
public record Contact(
        Long id,
        String name,
        String email,
        String phoneNumber) {
}
