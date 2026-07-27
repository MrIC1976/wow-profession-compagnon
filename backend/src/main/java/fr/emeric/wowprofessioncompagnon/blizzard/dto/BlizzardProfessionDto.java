package fr.emeric.wowprofessioncompagnon.blizzard.dto;

/**
 * Représente une profession Blizzard.
 */
public record BlizzardProfessionDto(

        int id,
        String name,
        BlizzardKeyDto key

) {
}