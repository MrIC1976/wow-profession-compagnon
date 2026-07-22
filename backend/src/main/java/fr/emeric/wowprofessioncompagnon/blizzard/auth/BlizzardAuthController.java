package fr.emeric.wowprofessioncompagnon.blizzard.auth;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint temporaire permettant de vérifier l'authentification Blizzard.
 */
@RestController
@RequestMapping("/api/blizzard")
public class BlizzardAuthController {

    private final BlizzardTokenService tokenService;

    public BlizzardAuthController(
            BlizzardTokenService tokenService
    ) {
        this.tokenService = tokenService;
    }

    /**
     * Vérifie qu'un jeton Blizzard peut être obtenu.
     *
     * Le jeton lui-même n'est jamais envoyé au navigateur.
     *
     * @return état de la connexion Blizzard
     */
    @GetMapping("/auth-status")
    public Map<String, Object> getAuthenticationStatus() {
        String accessToken = tokenService.getAccessToken();

        return Map.of(
                "authenticated", true,
                "tokenPresent", !accessToken.isBlank()
        );
    }
}