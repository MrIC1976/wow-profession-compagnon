package fr.emeric.wowprofessioncompagnon.blizzard.auth;

import fr.emeric.wowprofessioncompagnon.blizzard.config.BlizzardProperties;

import java.time.Instant;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * Récupère et met en cache le jeton OAuth Blizzard.
 */
@Service
public class BlizzardTokenService {

    /**
     * Le jeton est considéré comme expiré légèrement avant sa vraie
     * expiration afin d'éviter de l'utiliser pendant sa dernière minute.
     */
    private static final long EXPIRATION_MARGIN_SECONDS = 60;

    private final RestClient restClient;
    private final BlizzardProperties properties;

    private String cachedAccessToken;
    private Instant cachedTokenExpiration = Instant.EPOCH;

    public BlizzardTokenService(
            RestClient blizzardRestClient,
            BlizzardProperties properties
    ) {
        this.restClient = blizzardRestClient;
        this.properties = properties;
    }

    /**
     * Retourne un jeton encore valide ou en demande un nouveau.
     *
     * @return jeton OAuth Blizzard
     */
    public synchronized String getAccessToken() {
        if (isCachedTokenValid()) {
            return cachedAccessToken;
        }

        validateConfiguration();

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");

        BlizzardTokenResponse response = restClient
                .post()
                .uri(properties.oauthUrl())
                .headers(headers -> headers.setBasicAuth(
                        properties.clientId(),
                        properties.clientSecret()
                ))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(BlizzardTokenResponse.class);

        if (response == null
                || response.accessToken() == null
                || response.accessToken().isBlank()) {

            throw new IllegalStateException(
                    "Blizzard n'a retourné aucun jeton OAuth."
            );
        }

        cachedAccessToken = response.accessToken();

        long validityDuration = Math.max(
                response.expiresIn() - EXPIRATION_MARGIN_SECONDS,
                1
        );

        cachedTokenExpiration =
                Instant.now().plusSeconds(validityDuration);

        return cachedAccessToken;
    }

    /**
     * Indique si le jeton actuellement mémorisé peut encore être utilisé.
     */
    private boolean isCachedTokenValid() {
        return cachedAccessToken != null
                && !cachedAccessToken.isBlank()
                && Instant.now().isBefore(cachedTokenExpiration);
    }

    /**
     * Vérifie que les identifiants Blizzard sont bien configurés.
     */
    private void validateConfiguration() {
        if (properties.clientId() == null
                || properties.clientId().isBlank()) {

            throw new IllegalStateException(
                    "La variable BLIZZARD_CLIENT_ID n'est pas définie."
            );
        }

        if (properties.clientSecret() == null
                || properties.clientSecret().isBlank()) {

            throw new IllegalStateException(
                    "La variable BLIZZARD_CLIENT_SECRET n'est pas définie."
            );
        }

        if (properties.oauthUrl() == null
                || properties.oauthUrl().isBlank()) {

            throw new IllegalStateException(
                    "L'adresse OAuth Blizzard n'est pas configurée."
            );
        }
    }
}