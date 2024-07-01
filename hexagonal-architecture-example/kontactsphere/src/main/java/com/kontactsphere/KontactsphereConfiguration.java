package com.kontactsphere;

import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import com.kontactsphere.domain.service.CreateContactService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class KontactsphereConfiguration {
    private final CreateContactRepository createContactRepository;

    @Bean
    // On peut aussi passer par des custom annotation (voir plus tard)
    public CreateContactUseCase createContactUseCase() {
        return new CreateContactService(createContactRepository);
    }
}
