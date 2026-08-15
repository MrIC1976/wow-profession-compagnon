package fr.emeric.wowprofessioncompagnon.item.model;

/**
 * Représente un objet World of Warcraft dans le domaine métier.
 *
 * Ce modèle est indépendant de la persistance JPA
 * et de l'API Blizzard.
 */
public record Item(
        int id,
        String name
) {
}