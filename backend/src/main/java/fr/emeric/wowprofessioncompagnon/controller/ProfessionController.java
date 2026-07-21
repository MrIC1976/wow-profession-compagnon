package fr.emeric.wowprofessioncompagnon.controller;

import fr.emeric.wowprofessioncompagnon.dto.ProfessionResponse;
import fr.emeric.wowprofessioncompagnon.service.ProfessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ============================================================================
 * Fichier : ProfessionController.java
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Ce contrôleur expose les endpoints REST liés aux métiers.
 *
 * Le frontend Angular appellera ces endpoints.
 *
 * Pour le moment, les données sont simulées.
 * Dans les prochains chapitres, elles seront récupérées
 * automatiquement depuis l'API Blizzard.
 * ============================================================================
 */
@RestController
public class ProfessionController {

    /**
     * Service métier.
     */
    private final ProfessionService professionService;

    /**
     * Injection du service.
     *
     * @param professionService service des métiers
     */
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    /**
     * Retourne tous les métiers.
     *
     * URL :
     * GET /api/professions
     *
     * @return liste des métiers
     */
    @GetMapping("/api/professions")
    public List<ProfessionResponse> getAllProfessions() {

        return professionService.getAllProfessions();

    }

}