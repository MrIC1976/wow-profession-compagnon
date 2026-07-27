package fr.emeric.wowprofessioncompagnon.recipe.repository;

import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository permettant d'accéder aux recettes stockées en base de données.
 */
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
}