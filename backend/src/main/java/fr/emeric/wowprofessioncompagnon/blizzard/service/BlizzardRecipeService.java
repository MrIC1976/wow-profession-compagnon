package fr.emeric.wowprofessioncompagnon.blizzard.service;

import fr.emeric.wowprofessioncompagnon.blizzard.client.BlizzardClient;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import org.springframework.stereotype.Service;

/**
 * Service permettant de récupérer une recette depuis l'API Blizzard.
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
     * @param recipeId identifiant Blizzard de la recette
     * @return recette Blizzard
     */
    public BlizzardRecipeDto getRecipe(int recipeId) {
        return blizzardClient.get(
                "/data/wow/recipe/" + recipeId,
                BlizzardRecipeDto.class
        );
    }
}