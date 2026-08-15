package fr.emeric.wowprofessioncompagnon.blizzard.service;

import fr.emeric.wowprofessioncompagnon.blizzard.client.BlizzardClient;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionIndexResponse;
import fr.emeric.wowprofessioncompagnon.profession.mapper.ProfessionMapper;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service permettant de récupérer les professions depuis l'API Blizzard.
 */
@Service
public class BlizzardProfessionService {

    private final BlizzardClient blizzardClient;

    public BlizzardProfessionService(
            BlizzardClient blizzardClient
    ) {
        this.blizzardClient = blizzardClient;
    }

    /**
     * Retourne les professions Blizzard converties en objets métier.
     *
     * @return liste des professions métier
     */
    public List<Profession> getProfessions() {

        BlizzardProfessionIndexResponse response = blizzardClient.get(
                "/data/wow/profession/index",
                BlizzardProfessionIndexResponse.class
        );

        if (response == null || response.professions() == null) {
            return List.of();
        }

        return ProfessionMapper.toDomain(response.professions());
    }
}