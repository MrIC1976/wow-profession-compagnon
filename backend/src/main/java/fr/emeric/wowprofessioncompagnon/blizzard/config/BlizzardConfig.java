package fr.emeric.wowprofessioncompagnon.blizzard.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuration générale du module Blizzard.
 */
@Configuration
@EnableConfigurationProperties(BlizzardProperties.class)
public class BlizzardConfig {

    /**
     * Client HTTP réutilisé pour les appels OAuth et API Blizzard.
     *
     * @return client HTTP Spring
     */
    @Bean
    public RestClient blizzardRestClient() {
        return RestClient.create();
    }
}