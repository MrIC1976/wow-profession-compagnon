package fr.emeric.wowprofessioncompagnon.blizzard.dto;

/**
 * Représente une référence vers un objet Blizzard.
 *
 * Cette structure est notamment utilisée pour les objets fabriqués
 * par une recette ainsi que pour ses composants.
 */
public record BlizzardItemReferenceDto(

        BlizzardKeyDto key,

        String name,

        int id

) {
}