package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Représente le détail d'une profession retourné par l'API Blizzard.
 */
public record BlizzardProfessionDetailDto(

        int id,

        String name,

        String description,

        BlizzardProfessionTypeDto type,

        BlizzardMediaDto media,

        @JsonProperty("skill_tiers")
        List<BlizzardSkillTierDto> skillTiers

) {
}