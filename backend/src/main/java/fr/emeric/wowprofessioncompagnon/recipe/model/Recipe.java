package fr.emeric.wowprofessioncompagnon.recipe.model;

import fr.emeric.wowprofessioncompagnon.item.model.Item;

import java.util.List;

/**
 * Représente une recette dans le domaine métier.
 *
 * Cette classe est indépendante de la persistance JPA
 * et de l'API Blizzard.
 */
public record Recipe(
        int id,
        String name,
        String description,
        Double craftedQuantity,
        Item allianceCraftedItem,
        Item hordeCraftedItem,
        List<RecipeReagent> reagents
) {
}