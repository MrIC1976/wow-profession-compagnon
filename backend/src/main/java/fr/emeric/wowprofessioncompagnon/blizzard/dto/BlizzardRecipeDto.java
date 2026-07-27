package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Représente une recette renvoyée par l'API Blizzard.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BlizzardRecipeDto(

        @JsonProperty("id")
        int id,

        @JsonProperty("name")
        String name

) {
}