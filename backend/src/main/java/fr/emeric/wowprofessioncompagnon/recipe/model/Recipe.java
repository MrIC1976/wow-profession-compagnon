package fr.emeric.wowprofessioncompagnon.recipe.model;

/**
 * Représente une recette dans le domaine métier.
 *
 * Cette classe est indépendante de la persistance (JPA)
 * et de l'API Blizzard.
 */
public record Recipe(
        int id,
        String name
) {
}