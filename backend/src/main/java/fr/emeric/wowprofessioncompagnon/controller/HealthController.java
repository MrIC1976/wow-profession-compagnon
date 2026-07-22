package fr.emeric.wowprofessioncompagnon.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Contrôleur permettant de vérifier que le backend fonctionne.
 *
 * L'annotation CrossOrigin autorise le frontend Angular,
 * exécuté sur le port 4200, à appeler ce contrôleur.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HealthController {

    /**
     * Retourne des informations simples sur l'état du backend.
     *
     * URL :
     * GET http://localhost:8080/api/health
     *
     * @return informations sur l'état de l'application
     */
    @GetMapping("/api/health")
    public Map<String, Object> health() {

        return Map.of(
                "status", "UP",
                "application", "WoW Profession Compagnon Backend",
                "version", "0.1.0"
        );
    }
}