package fr.emeric.wowprofessioncompagnon.profession.service;

import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardProfessionService;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.synchronization.ProfessionSynchronizationService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service métier des professions.
 *
 * Cette classe constitue le point d'entrée du domaine Profession.
 * Elle permet de consulter les professions et de déclencher leur
 * synchronisation depuis l'API Blizzard vers PostgreSQL.
 */
@Service
public class ProfessionService {

    private final BlizzardProfessionService blizzardProfessionService;
    private final ProfessionSynchronizationService professionSynchronizationService;

    public ProfessionService(
            BlizzardProfessionService blizzardProfessionService,
            ProfessionSynchronizationService professionSynchronizationService
    ) {
        this.blizzardProfessionService = blizzardProfessionService;
        this.professionSynchronizationService = professionSynchronizationService;
    }

    /**
     * Retourne la liste des professions.
     *
     * Pour le moment, les données sont encore récupérées directement
     * depuis l'API Blizzard.
     *
     * @return liste des professions
     */
    public List<Profession> getProfessions() {
        return blizzardProfessionService.getProfessions();
    }

    /**
     * Synchronise les professions Blizzard dans PostgreSQL.
     *
     * @return nombre de professions enregistrées ou mises à jour
     */
    public int synchronizeProfessions() {
        return professionSynchronizationService
                .synchronizeProfessions()
                .size();
    }

}