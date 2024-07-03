package com.kontactsphere;

import com.kontactsphere.domain.port.api.CreateContactUseCase;
import com.kontactsphere.domain.port.api.ReadContactUseCase;
import com.kontactsphere.domain.port.spi.CreateContactRepository;
import com.kontactsphere.domain.port.spi.ReadContactRepository;
import com.kontactsphere.domain.service.CreateContactService;
import com.kontactsphere.domain.service.ReadContactService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
// TODO : ajouter plutot de nouvelles annotation au lieu de faire le binding ici
public class KontactsphereConfiguration {
    private final CreateContactRepository createContactRepository;
    private final ReadContactRepository readContactRepository;

    @Bean
    public CreateContactUseCase createContactUseCase() {
        return new CreateContactService(createContactRepository);
    }

    @Bean
    public ReadContactUseCase readContactUseCase() {
        return new ReadContactService(readContactRepository);
    }
}
