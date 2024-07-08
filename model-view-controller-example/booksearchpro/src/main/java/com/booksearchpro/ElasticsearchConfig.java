package com.booksearchpro;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;

    @Bean(destroyMethod = "close")
    public RestClient restClient() {
        return RestClient.builder(HttpHost.create(elasticsearchUrl)).build();
    }
}
