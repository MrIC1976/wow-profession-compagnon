package fr.emeric.wowprofessioncompagnon.recipe.mapper;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.recipe.model.Recipe;

import java.util.List;

/**
 * Mapper permettant de convertir les DTO Blizzard en objets métier.
 */
public final class RecipeMapper {

    private RecipeMapper() {
        // Classe utilitaire
    }

    /**
     * Convertit une recette Blizzard en objet métier.
     *
     * @param dto recette Blizzard
     * @return recette métier
     */
    public static Recipe toDomain(BlizzardRecipeDto dto) {

        return new Recipe(
                dto.id(),
                dto.name()
        );
    }

    /**
     * Convertit une liste de recettes Blizzard.
     *
     * @param recipes liste Blizzard
     * @return liste métier
     */
    public static List<Recipe> toDomain(List<BlizzardRecipeDto> recipes) {

        return recipes.stream()
                .map(RecipeMapper::toDomain)
                .toList();
    }

}