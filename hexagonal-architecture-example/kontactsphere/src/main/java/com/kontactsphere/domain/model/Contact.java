package com.kontactsphere.domain.model;

import com.kontactsphere.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Builder
@AllArgsConstructor
@Getter
public class Contact {
    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public void validate() {
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("Le nom est obligatoire !");
        }
        if (StringUtils.isAllBlank(email, phoneNumber)) {
            throw new BusinessException("Au moins une information de contact est requise !");
        }
    }
}
