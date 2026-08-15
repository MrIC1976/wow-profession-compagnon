package fr.emeric.wowprofessioncompagnon.blizzard.service;

import fr.emeric.wowprofessioncompagnon.blizzard.client.BlizzardClient;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDetailDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;

import org.springframework.stereotype.Service;

/**
 * Service permettant de récupérer les informations
 * d'une recette depuis l'API Blizzard.
 */
@Service
public class BlizzardRecipeService {

    private final BlizzardClient blizzardClient;

    public BlizzardRecipeService(
            BlizzardClient blizzardClient
    ) {
        this.blizzardClient = blizzardClient;
    }

    /**
     * Retourne une recette Blizzard.
     *
     * Cette méthode est conservée pour les fonctionnalités
     * existantes de synchronisation des recettes.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette Blizzard
     */
    public BlizzardRecipeDto getRecipe(int recipeId) {
        return blizzardClient.get(
                "/data/wow/recipe/" + recipeId,
                BlizzardRecipeDto.class
        );
    }

    /**
     * Retourne le détail d'une recette Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return détail de la recette Blizzard
     */
    public BlizzardRecipeDetailDto getRecipeDetail(int recipeId) {
        return blizzardClient.get(
                "/data/wow/recipe/" + recipeId,
                BlizzardRecipeDetailDto.class
        );
    }

    /**
     * Retourne la réponse JSON brute d'une recette Blizzard.
     *
     * Cette méthode est temporairement utilisée pour analyser
     * précisément la structure retournée par l'API Blizzard.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return JSON Blizzard brut
     */
    public String getRecipeRaw(int recipeId) {
        return blizzardClient.getRaw(
                "/data/wow/recipe/" + recipeId
        );
    }
}