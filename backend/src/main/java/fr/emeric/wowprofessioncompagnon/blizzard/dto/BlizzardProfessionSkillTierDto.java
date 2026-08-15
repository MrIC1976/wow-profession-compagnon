package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Représente un niveau de compétence d'une profession Blizzard.
 */
public record BlizzardProfessionSkillTierDto(

        int id,

        String name,

        @JsonProperty("categories")
        List<BlizzardCategoryDto> categories

) {
}