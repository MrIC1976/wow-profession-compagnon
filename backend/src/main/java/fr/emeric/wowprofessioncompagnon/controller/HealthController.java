package fr.emeric.wowprofessioncompagnon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Contrôleur permettant de vérifier que le backend fonctionne.
 */
@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, Object> health() {

        return Map.of(
                "status", "UP",
                "application", "WoW Profession Compagnon Backend",
                "version", "0.1.0"
        );

    }
}