package fr.emeric.wowprofessioncompagnon.profession.controller;

import fr.emeric.wowprofessioncompagnon.common.dto.SynchronizationResponse;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.service.ProfessionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST officiel du domaine Profession.
 */
@RestController
@RequestMapping("/api/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    public ProfessionController(
            ProfessionService professionService
    ) {
        this.professionService = professionService;
    }

    /**
     * Retourne la liste des professions disponibles.
     *
     * @return liste des professions
     */
    @GetMapping
    public List<Profession> getProfessions() {
        return professionService.getProfessions();
    }

    /**
     * Synchronise les professions Blizzard avec la base de données.
     *
     * @return nombre de professions synchronisées
     */
    @PostMapping("/synchronize")
    public SynchronizationResponse synchronizeProfessions() {
        return new SynchronizationResponse(
                professionService.synchronizeProfessions()
        );
    }

}