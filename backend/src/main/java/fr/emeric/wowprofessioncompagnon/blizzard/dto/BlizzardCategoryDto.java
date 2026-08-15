package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import java.util.List;

/**
 * Représente une catégorie d'un niveau de compétence Blizzard.
 */
public record BlizzardCategoryDto(

        String name,

        List<BlizzardRecipeReferenceDto> recipes

) {
}