package com.kontactsphere.config;

import com.kontactsphere.domain.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.kontactsphere.domain",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                UseCase.class
        })}
)
public class KontactsphereConf {
    // R.A.S
}
