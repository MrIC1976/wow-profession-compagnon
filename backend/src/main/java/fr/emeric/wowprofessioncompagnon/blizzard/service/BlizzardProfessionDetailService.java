package fr.emeric.wowprofessioncompagnon.blizzard.service;

import fr.emeric.wowprofessioncompagnon.blizzard.client.BlizzardClient;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionDetailDto;

import org.springframework.stereotype.Service;

/**
 * Service permettant de récupérer le détail d'une profession
 * depuis l'API Blizzard.
 */
@Service
public class BlizzardProfessionDetailService {

    private final BlizzardClient blizzardClient;

    public BlizzardProfessionDetailService(
            BlizzardClient blizzardClient
    ) {
        this.blizzardClient = blizzardClient;
    }

    /**
     * Retourne le détail d'une profession Blizzard.
     *
     * @param professionId identifiant Blizzard de la profession
     * @return détail de la profession
     */
    public BlizzardProfessionDetailDto getProfessionDetail(int professionId) {
        return blizzardClient.get(
                "/data/wow/profession/" + professionId,
                BlizzardProfessionDetailDto.class
        );
    }
}