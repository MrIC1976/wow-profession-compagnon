package fr.emeric.wowprofessioncompagnon.recipe.mapper;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDto;
import fr.emeric.wowprofessioncompagnon.item.mapper.ItemMapper;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeReagentEntity;
import fr.emeric.wowprofessioncompagnon.recipe.model.Recipe;
import fr.emeric.wowprofessioncompagnon.recipe.model.RecipeReagent;

import java.util.List;

/**
 * Mapper permettant de convertir les différentes représentations
 * d'une recette en objets métier.
 */
public final class RecipeMapper {

    private RecipeMapper() {
        // Classe utilitaire
    }

    /**
     * Convertit une recette Blizzard simplifiée en objet métier.
     *
     * @param dto recette Blizzard
     * @return recette métier
     */
    public static Recipe toDomain(
            BlizzardRecipeDto dto
    ) {
        return new Recipe(
                dto.id(),
                dto.name(),
                null,
                null,
                null,
                null,
                List.of()
        );
    }

    /**
     * Convertit une recette persistée et ses composants
     * en objet métier.
     *
     * @param entity recette stockée en base
     * @param reagents composants stockés en base
     * @return recette métier
     */
    public static Recipe toDomain(
            RecipeEntity entity,
            List<RecipeReagentEntity> reagents
    ) {
        List<RecipeReagent> domainReagents = reagents.stream()
                .map(RecipeMapper::toDomain)
                .toList();

        return new Recipe(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCraftedQuantity(),
                ItemMapper.toDomain(entity.getAllianceCraftedItem()),
                ItemMapper.toDomain(entity.getHordeCraftedItem()),
                domainReagents
        );
    }

    /**
     * Convertit un composant persisté en composant métier.
     *
     * @param entity composant stocké en base
     * @return composant métier
     */
    public static RecipeReagent toDomain(
            RecipeReagentEntity entity
    ) {
        return new RecipeReagent(
                ItemMapper.toDomain(entity.getReagentItem()),
                entity.getQuantity(),
                entity.getRecraftQuantity()
        );
    }

    /**
     * Convertit une liste de recettes Blizzard.
     *
     * @param recipes liste Blizzard
     * @return liste métier
     */
    public static List<Recipe> toDomain(
            List<BlizzardRecipeDto> recipes
    ) {
        return recipes.stream()
                .map(RecipeMapper::toDomain)
                .toList();
    }
}