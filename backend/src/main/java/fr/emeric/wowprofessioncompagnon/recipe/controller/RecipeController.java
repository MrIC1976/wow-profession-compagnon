package fr.emeric.wowprofessioncompagnon.recipe.controller;

import fr.emeric.wowprofessioncompagnon.common.dto.SynchronizationResponse;
import fr.emeric.wowprofessioncompagnon.recipe.model.Recipe;
import fr.emeric.wowprofessioncompagnon.recipe.service.RecipeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST officiel du domaine Recipe.
 *
 * Les données métier exposées par ce contrôleur sont lues
 * depuis PostgreSQL. La synchronisation permet de mettre
 * à jour ces données depuis l'API Blizzard.
 */
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(
            RecipeService recipeService
    ) {
        this.recipeService = recipeService;
    }

    /**
     * Retourne une recette enregistrée dans PostgreSQL.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette enregistrée ou HTTP 404
     */
    @GetMapping("/{recipeId}/database")
    public ResponseEntity<Recipe> getRecipeFromDatabase(
            @PathVariable int recipeId
    ) {
        return recipeService
                .getRecipeFromDatabase(recipeId)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    /**
     * Synchronise une recette Blizzard avec PostgreSQL.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return nombre de recettes synchronisées
     */
    @PostMapping("/{recipeId}/synchronize")
    public SynchronizationResponse synchronizeRecipe(
            @PathVariable int recipeId
    ) {
        return new SynchronizationResponse(
                recipeService.synchronizeRecipe(recipeId)
        );
    }
}