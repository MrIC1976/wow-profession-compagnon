package fr.emeric.wowprofessioncompagnon.recipe.synchronization;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardItemReferenceDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeDetailDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeReagentDto;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardRecipeService;
import fr.emeric.wowprofessioncompagnon.item.entity.ItemEntity;
import fr.emeric.wowprofessioncompagnon.item.mapper.ItemMapper;
import fr.emeric.wowprofessioncompagnon.item.repository.ItemRepository;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeReagentEntity;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeReagentRepository;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service permettant de synchroniser les recettes entre
 * l'API Blizzard et PostgreSQL.
 */
@Service
public class RecipeSynchronizationService {

    private static final String UNKNOWN_ITEM_NAME_PREFIX =
            "Objet Blizzard ";

    private final BlizzardRecipeService blizzardRecipeService;
    private final RecipeRepository recipeRepository;
    private final RecipeReagentRepository recipeReagentRepository;
    private final ItemRepository itemRepository;

    public RecipeSynchronizationService(
            BlizzardRecipeService blizzardRecipeService,
            RecipeRepository recipeRepository,
            RecipeReagentRepository recipeReagentRepository,
            ItemRepository itemRepository
    ) {
        this.blizzardRecipeService = blizzardRecipeService;
        this.recipeRepository = recipeRepository;
        this.recipeReagentRepository = recipeReagentRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Synchronise une recette Blizzard ainsi que les objets
     * et composants qui lui sont associés.
     *
     * La recette n'est marquée comme synchronisée en détail
     * qu'une fois l'ensemble de l'opération terminé.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return liste des recettes synchronisées
     */
    @Transactional
    public List<RecipeEntity> synchronizeRecipes(
            int recipeId
    ) {
        BlizzardRecipeDetailDto recipe =
                blizzardRecipeService.getRecipeDetail(recipeId);

        if (recipe == null) {
            return List.of();
        }

        ItemEntity allianceCraftedItem =
                synchronizeItem(
                        recipe.allianceCraftedItem()
                );

        ItemEntity hordeCraftedItem =
                synchronizeItem(
                        recipe.hordeCraftedItem()
                );

        RecipeEntity entity =
                new RecipeEntity(
                        recipe.id(),
                        recipe.name(),
                        recipe.description(),
                        getCraftedQuantity(recipe),
                        allianceCraftedItem,
                        hordeCraftedItem
                );

        RecipeEntity savedRecipe =
                recipeRepository.save(entity);

        recipeReagentRepository.deleteAllByRecipeId(
                savedRecipe.getId()
        );

        synchronizeReagents(
                savedRecipe,
                recipe.reagents()
        );

        savedRecipe.setDetailSynchronized(true);

        RecipeEntity synchronizedRecipe =
                recipeRepository.save(savedRecipe);

        return List.of(
                synchronizedRecipe
        );
    }

    /**
     * Synchronise une référence d'objet Blizzard.
     *
     * Blizzard peut exceptionnellement retourner une référence
     * contenant un identifiant mais aucun nom.
     *
     * Si l'objet existe déjà dans PostgreSQL, son nom actuel
     * est conservé. Sinon, un nom technique temporaire est utilisé
     * afin de respecter la contrainte NOT NULL de la base.
     *
     * @param item objet Blizzard
     * @return objet persisté ou null
     */
    private ItemEntity synchronizeItem(
            BlizzardItemReferenceDto item
    ) {
        if (item == null) {
            return null;
        }

        if (hasUsableName(item.name())) {
            return itemRepository.save(
                    ItemMapper.toEntity(item)
            );
        }

        return itemRepository
                .findById(item.id())
                .orElseGet(
                        () -> itemRepository.save(
                                new ItemEntity(
                                        item.id(),
                                        UNKNOWN_ITEM_NAME_PREFIX
                                                + item.id()
                                )
                        )
                );
    }

    /**
     * Indique si un nom Blizzard est exploitable.
     *
     * @param name nom à vérifier
     * @return true si le nom n'est ni null ni vide
     */
    private boolean hasUsableName(
            String name
    ) {
        return name != null
                && !name.isBlank();
    }

    /**
     * Synchronise les composants d'une recette.
     *
     * @param recipe recette persistée
     * @param reagents composants Blizzard
     */
    private void synchronizeReagents(
            RecipeEntity recipe,
            List<BlizzardRecipeReagentDto> reagents
    ) {
        if (reagents == null || reagents.isEmpty()) {
            return;
        }

        List<RecipeReagentEntity> entities =
                reagents.stream()
                        .filter(reagent ->
                                reagent != null
                                        && reagent.reagent() != null
                        )
                        .map(reagent -> {
                            ItemEntity item =
                                    synchronizeItem(
                                            reagent.reagent()
                                    );

                            return new RecipeReagentEntity(
                                    recipe,
                                    item,
                                    reagent.quantity(),
                                    reagent.recraftQuantity()
                            );
                        })
                        .toList();

        recipeReagentRepository.saveAll(entities);
    }

    /**
     * Retourne la quantité produite par la recette.
     *
     * @param recipe recette Blizzard
     * @return quantité produite ou null
     */
    private Double getCraftedQuantity(
            BlizzardRecipeDetailDto recipe
    ) {
        if (recipe.craftedQuantity() == null) {
            return null;
        }

        return recipe.craftedQuantity().value();
    }
}