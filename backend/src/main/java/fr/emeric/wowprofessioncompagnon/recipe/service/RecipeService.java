package fr.emeric.wowprofessioncompagnon.recipe.service;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardRecipeService;
import fr.emeric.wowprofessioncompagnon.recipe.synchronization.RecipeSynchronizationService;
import org.springframework.stereotype.Service;

/**
 * Service métier des recettes.
 *
 * Cette classe constitue le point d'entrée du domaine Recipe.
 * Elle permet de consulter une recette et de déclencher sa
 * synchronisation depuis l'API Blizzard vers PostgreSQL.
 */
@Service
public class RecipeService {

    private final BlizzardRecipeService blizzardRecipeService;
    private final RecipeSynchronizationService recipeSynchronizationService;

    public RecipeService(
            BlizzardRecipeService blizzardRecipeService,
            RecipeSynchronizationService recipeSynchronizationService
    ) {
        this.blizzardRecipeService = blizzardRecipeService;
        this.recipeSynchronizationService = recipeSynchronizationService;
    }

    /**
     * Retourne une recette depuis l'API Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette Blizzard
     */
    public BlizzardRecipeDto getRecipe(int recipeId) {
        return blizzardRecipeService.getRecipe(recipeId);
    }

    /**
     * Synchronise une recette Blizzard dans PostgreSQL.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return nombre de recettes enregistrées ou mises à jour
     */
    public int synchronizeRecipe(int recipeId) {
        return recipeSynchronizationService
                .synchronizeRecipes(recipeId)
                .size();
    }
}