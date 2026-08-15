package fr.emeric.wowprofessioncompagnon.recipe.service;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDetailDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardRecipeService;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeReagentEntity;
import fr.emeric.wowprofessioncompagnon.recipe.mapper.RecipeMapper;
import fr.emeric.wowprofessioncompagnon.recipe.model.Recipe;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeReagentRepository;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeRepository;
import fr.emeric.wowprofessioncompagnon.recipe.synchronization.RecipeSynchronizationService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service métier des recettes.
 *
 * Cette classe constitue le point d'entrée du domaine Recipe.
 * Elle permet de consulter les recettes depuis Blizzard,
 * de consulter les recettes persistées dans PostgreSQL
 * et de déclencher leur synchronisation.
 */
@Service
public class RecipeService {

    private final BlizzardRecipeService blizzardRecipeService;
    private final RecipeRepository recipeRepository;
    private final RecipeReagentRepository recipeReagentRepository;
    private final RecipeSynchronizationService recipeSynchronizationService;

    public RecipeService(
            BlizzardRecipeService blizzardRecipeService,
            RecipeRepository recipeRepository,
            RecipeReagentRepository recipeReagentRepository,
            RecipeSynchronizationService recipeSynchronizationService
    ) {
        this.blizzardRecipeService = blizzardRecipeService;
        this.recipeRepository = recipeRepository;
        this.recipeReagentRepository = recipeReagentRepository;
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
     * Retourne le détail d'une recette depuis l'API Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return détail de la recette Blizzard
     */
    public BlizzardRecipeDetailDto getRecipeDetail(int recipeId) {
        return blizzardRecipeService.getRecipeDetail(recipeId);
    }

    /**
     * Retourne une recette enregistrée dans PostgreSQL
     * ainsi que ses objets fabriqués et ses composants.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette métier si elle existe
     */
    public Optional<Recipe> getRecipeFromDatabase(int recipeId) {

        Optional<RecipeEntity> recipe =
                recipeRepository.findByIdWithCraftedItems(recipeId);

        if (recipe.isEmpty()) {
            return Optional.empty();
        }

        List<RecipeReagentEntity> reagents =
                recipeReagentRepository.findAllByRecipeId(recipeId);

        return Optional.of(
                RecipeMapper.toDomain(
                        recipe.get(),
                        reagents
                )
        );
    }

    /**
     * Retourne la réponse JSON brute d'une recette Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return JSON Blizzard brut
     */
    public String getRecipeRaw(int recipeId) {
        return blizzardRecipeService.getRecipeRaw(recipeId);
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