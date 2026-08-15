package fr.emeric.wowprofessioncompagnon.blizzard.service;

import fr.emeric.wowprofessioncompagnon.blizzard.client.BlizzardClient;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionSkillTierDto;

import org.springframework.stereotype.Service;

/**
 * Service permettant de récupérer un niveau de compétence
 * d'une profession depuis l'API Blizzard.
 */
@Service
public class BlizzardProfessionSkillTierService {

    private final BlizzardClient blizzardClient;

    public BlizzardProfessionSkillTierService(
            BlizzardClient blizzardClient
    ) {
        this.blizzardClient = blizzardClient;
    }

    /**
     * Retourne le détail d'un niveau de compétence Blizzard.
     *
     * @param professionId identifiant Blizzard de la profession
     * @param skillTierId identifiant Blizzard du niveau de compétence
     * @return niveau de compétence Blizzard
     */
    public BlizzardProfessionSkillTierDto getSkillTier(
            int professionId,
            int skillTierId
    ) {
        return blizzardClient.get(
                "/data/wow/profession/"
                        + professionId
                        + "/skill-tier/"
                        + skillTierId,
                BlizzardProfessionSkillTierDto.class
        );
    }
}