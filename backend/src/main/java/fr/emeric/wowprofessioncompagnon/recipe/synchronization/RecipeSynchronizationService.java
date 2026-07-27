package fr.emeric.wowprofessioncompagnon.recipe.synchronization;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardRecipeService;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service permettant de synchroniser les recettes entre l'API Blizzard
 * et la base de données.
 */
@Service
public class RecipeSynchronizationService {

    private final BlizzardRecipeService blizzardRecipeService;
    private final RecipeRepository recipeRepository;

    public RecipeSynchronizationService(
            BlizzardRecipeService blizzardRecipeService,
            RecipeRepository recipeRepository
    ) {
        this.blizzardRecipeService = blizzardRecipeService;
        this.recipeRepository = recipeRepository;
    }

    /**
     * Synchronise une recette Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return liste des recettes synchronisées
     */
    @Transactional
    public List<RecipeEntity> synchronizeRecipes(int recipeId) {

        BlizzardRecipeDto recipe =
                blizzardRecipeService.getRecipe(recipeId);

        if (recipe == null) {
            return List.of();
        }

        List<RecipeEntity> entities = List.of(
                new RecipeEntity(
                        recipe.id(),
                        recipe.name()
                )
        );

        return recipeRepository.saveAll(entities);
    }
}