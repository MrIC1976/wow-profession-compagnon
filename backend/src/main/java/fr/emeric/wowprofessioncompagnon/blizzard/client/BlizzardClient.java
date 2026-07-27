package fr.emeric.wowprofessioncompagnon.blizzard.client;

import fr.emeric.wowprofessioncompagnon.blizzard.auth.BlizzardTokenService;
import fr.emeric.wowprofessioncompagnon.blizzard.config.BlizzardProperties;
import fr.emeric.wowprofessioncompagnon.common.exception.BlizzardApiException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * Client générique permettant de communiquer avec l'API Blizzard.
 *
 * Toutes les requêtes HTTP vers Blizzard doivent passer par cette classe.
 */
@Component
public class BlizzardClient {

    private final RestClient restClient;
    private final BlizzardTokenService tokenService;
    private final BlizzardProperties properties;

    public BlizzardClient(
            RestClient blizzardRestClient,
            BlizzardTokenService tokenService,
            BlizzardProperties properties
    ) {
        this.restClient = blizzardRestClient;
        this.tokenService = tokenService;
        this.properties = properties;
    }

    /**
     * Exécute une requête GET vers l'API Blizzard.
     *
     * @param path chemin de l'API, par exemple /data/wow/profession/index
     * @param responseType type de réponse attendu
     * @param <T> type retourné
     * @return réponse désérialisée
     * @throws BlizzardApiException si l'appel à Blizzard échoue
     */
    public <T> T get(String path, Class<T> responseType) {
        String url = buildUrl(path);

        try {
            return restClient
                    .get()
                    .uri(url)
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            "Bearer " + tokenService.getAccessToken()
                    )
                    .retrieve()
                    .body(responseType);
        } catch (RestClientException exception) {
            throw new BlizzardApiException(
                    "Impossible de communiquer avec l'API Blizzard.",
                    exception
            );
        }
    }

    /**
     * Construit l'URL complète utilisée pour appeler l'API Blizzard.
     *
     * @param path chemin de la ressource Blizzard
     * @return URL complète
     */
    private String buildUrl(String path) {
        return properties.apiBaseUrl()
                + path
                + "?namespace=" + properties.namespace()
                + "&locale=" + properties.locale();
    }
}