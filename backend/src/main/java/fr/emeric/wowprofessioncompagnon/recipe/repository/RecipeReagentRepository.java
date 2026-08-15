package fr.emeric.wowprofessioncompagnon.recipe.repository;

import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeReagentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository permettant d'accéder aux composants
 * des recettes stockés en base de données.
 */
@Repository
public interface RecipeReagentRepository
        extends JpaRepository<RecipeReagentEntity, Long> {

    /**
     * Retourne tous les composants associés à une recette
     * avec leur objet associé chargé immédiatement.
     *
     * @param recipeId identifiant Blizzard de la recette
     * @return composants de la recette
     */
    @Query("""
            select reagent
            from RecipeReagentEntity reagent
            join fetch reagent.reagentItem
            where reagent.recipe.id = :recipeId
            order by reagent.reagentItem.id
            """)
    List<RecipeReagentEntity> findAllByRecipeId(
            @Param("recipeId") Integer recipeId
    );

    /**
     * Supprime directement en base tous les composants
     * associés à une recette.
     *
     * @param recipeId identifiant Blizzard de la recette
     */
    @Modifying
    @Query("""
            delete from RecipeReagentEntity reagent
            where reagent.recipe.id = :recipeId
            """)
    void deleteAllByRecipeId(
            @Param("recipeId") Integer recipeId
    );
}