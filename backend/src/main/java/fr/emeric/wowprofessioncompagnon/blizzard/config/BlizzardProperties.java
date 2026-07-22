package fr.emeric.wowprofessioncompagnon.blizzard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration nécessaire pour communiquer avec l'API Blizzard.
 *
 * @param clientId identifiant public de l'application Blizzard
 * @param clientSecret secret privé de l'application Blizzard
 * @param region région Battle.net utilisée
 * @param namespace namespace de l'API Game Data
 * @param locale langue des données demandées
 * @param oauthUrl adresse utilisée pour obtenir un jeton OAuth
 * @param apiBaseUrl adresse de base de l'API Blizzard
 */
@ConfigurationProperties(prefix = "blizzard")
public record BlizzardProperties(
        String clientId,
        String clientSecret,
        String region,
        String namespace,
        String locale,
        String oauthUrl,
        String apiBaseUrl
) {
}