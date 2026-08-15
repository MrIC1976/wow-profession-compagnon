package fr.emeric.wowprofessioncompagnon.profession.controller;

import fr.emeric.wowprofessioncompagnon.common.dto.SynchronizationResponse;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.model.ProfessionSkillTierDetail;
import fr.emeric.wowprofessioncompagnon.profession.service.ProfessionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST officiel du domaine Profession.
 *
 * Les données métier sont lues depuis PostgreSQL.
 * Blizzard est utilisé par les endpoints de synchronisation.
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

    @GetMapping
    public List<Profession> getProfessions() {
        return professionService.getProfessions();
    }

    @GetMapping("/{professionId}/detail")
    public ResponseEntity<Profession> getProfessionDetail(
            @PathVariable int professionId
    ) {
        return professionService
                .getProfessionDetail(professionId)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/{professionId}/skill-tiers/{skillTierId}")
    public ResponseEntity<ProfessionSkillTierDetail> getSkillTier(
            @PathVariable int professionId,
            @PathVariable int skillTierId
    ) {
        return professionService
                .getSkillTier(
                        professionId,
                        skillTierId
                )
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    /**
     * Synchronise toutes les professions,
     * sans synchroniser le contenu complet de leurs skill tiers.
     */
    @PostMapping("/synchronize")
    public SynchronizationResponse synchronizeProfessions() {
        return new SynchronizationResponse(
                professionService.synchronizeProfessions()
        );
    }

    /**
     * Synchronise complètement une seule profession :
     * détail, références et contenu de tous ses skill tiers.
     */
    @PostMapping("/{professionId}/synchronize")
    public SynchronizationResponse synchronizeProfession(
            @PathVariable int professionId
    ) {
        return new SynchronizationResponse(
                professionService.synchronizeProfession(
                        professionId
                )
        );
    }

    /**
     * Synchronise tous les skill tiers déjà connus
     * d'une profession.
     */
    @PostMapping("/{professionId}/skill-tiers/synchronize")
    public SynchronizationResponse synchronizeAllSkillTiers(
            @PathVariable int professionId
    ) {
        return new SynchronizationResponse(
                professionService.synchronizeAllSkillTiers(
                        professionId
                )
        );
    }

    /**
     * Synchronise un skill tier précis.
     */
    @PostMapping(
            "/{professionId}/skill-tiers/{skillTierId}/synchronize"
    )
    public SynchronizationResponse synchronizeSkillTier(
            @PathVariable int professionId,
            @PathVariable int skillTierId
    ) {
        return new SynchronizationResponse(
                professionService.synchronizeSkillTier(
                        professionId,
                        skillTierId
                )
        );
    }
}