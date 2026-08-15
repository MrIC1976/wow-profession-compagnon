package fr.emeric.wowprofessioncompagnon.blizzard.dto;

/**
 * Référence vers un niveau de compétence Blizzard.
 */
public record BlizzardSkillTierDto(

        BlizzardKeyDto key,

        int id,

        String name

) {
}