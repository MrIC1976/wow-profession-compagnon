package fr.emeric.wowprofessioncompagnon.recipe.model;

import fr.emeric.wowprofessioncompagnon.item.model.Item;

/**
 * Représente un composant nécessaire à la fabrication d'une recette.
 */
public record RecipeReagent(
        Item item,
        int quantity,
        Integer recraftQuantity
) {
}