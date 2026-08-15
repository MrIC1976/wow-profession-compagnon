package fr.emeric.wowprofessioncompagnon.profession.model;

/**
 * Représente une référence métier vers une recette
 * associée à une catégorie de skill tier.
 */
public record ProfessionRecipeReference(
        int id,
        String name
) {
}