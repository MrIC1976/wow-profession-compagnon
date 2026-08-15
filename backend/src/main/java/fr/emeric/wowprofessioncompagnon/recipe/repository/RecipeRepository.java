package fr.emeric.wowprofessioncompagnon.recipe.repository;

import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository permettant d'accéder aux recettes
 * stockées en base de données.
 */
@Repository
public interface RecipeRepository
        extends JpaRepository<RecipeEntity, Integer> {

    /**
     * Retourne une recette avec ses objets fabriqués
     * Alliance et Horde chargés immédiatement.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return recette si elle existe
     */
    @Query("""
            select recipe
            from RecipeEntity recipe
            left join fetch recipe.allianceCraftedItem
            left join fetch recipe.hordeCraftedItem
            where recipe.id = :recipeId
            """)
    Optional<RecipeEntity> findByIdWithCraftedItems(
            @Param("recipeId") Integer recipeId
    );
}