package fr.emeric.wowprofessioncompagnon.blizzard.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Réponse retournée par le serveur OAuth Blizzard.
 *
 * @param accessToken jeton d'accès
 * @param tokenType type du jeton
 * @param expiresIn durée de validité en secondes
 */
public record BlizzardTokenResponse(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        long expiresIn

) {
}