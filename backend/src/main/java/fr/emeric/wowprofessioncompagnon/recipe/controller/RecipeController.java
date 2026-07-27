package fr.emeric.wowprofessioncompagnon.recipe.controller;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST officiel du domaine Recipe.
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
     * Retourne une recette disponible depuis l'API Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette Blizzard
     */
    @GetMapping("/{recipeId}")
    public BlizzardRecipeDto getRecipe(
            @PathVariable int recipeId
    ) {
        return recipeService.getRecipe(recipeId);
    }
}