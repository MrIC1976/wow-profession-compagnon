package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Représente le détail d'une recette Blizzard.
 */
public record BlizzardRecipeDetailDto(

        int id,

        String name,

        String description,

        BlizzardMediaDto media,

        @JsonProperty("alliance_crafted_item")
        BlizzardItemReferenceDto allianceCraftedItem,

        @JsonProperty("horde_crafted_item")
        BlizzardItemReferenceDto hordeCraftedItem,

        List<BlizzardRecipeReagentDto> reagents,

        @JsonProperty("crafted_quantity")
        BlizzardCraftedQuantityDto craftedQuantity

) {
}