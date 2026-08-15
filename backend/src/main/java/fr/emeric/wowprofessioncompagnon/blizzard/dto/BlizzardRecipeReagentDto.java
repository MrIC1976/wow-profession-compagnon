package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Composant utilisé par une recette Blizzard.
 */
public record BlizzardRecipeReagentDto(

        BlizzardItemReferenceDto reagent,

        int quantity,

        @JsonProperty("recraft_quantity")
        Integer recraftQuantity

) {
}