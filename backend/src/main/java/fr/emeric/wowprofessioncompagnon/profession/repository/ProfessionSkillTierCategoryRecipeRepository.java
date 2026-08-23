package fr.emeric.wowprofessioncompagnon.profession.repository;

import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryRecipeEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository permettant d'accéder aux associations
 * entre catégories de skill tiers et recettes.
 */
@Repository
public interface ProfessionSkillTierCategoryRecipeRepository
        extends JpaRepository<ProfessionSkillTierCategoryRecipeEntity, Long> {

    /**
     * Retourne les associations d'une catégorie
     * avec les recettes chargées immédiatement.
     *
     * @param categoryId identifiant PostgreSQL de la catégorie
     * @return associations catégorie/recette
     */
    @Query("""
            select association
            from ProfessionSkillTierCategoryRecipeEntity association
            join fetch association.recipe
            where association.category.id = :categoryId
            order by association.recipe.id
            """)
    List<ProfessionSkillTierCategoryRecipeEntity> findAllByCategoryId(
            @Param("categoryId") Long categoryId
    );

    /**
     * Retourne les identifiants distincts des recettes
     * qui n'ont pas encore été synchronisées en détail
     * pour un skill tier.
     *
     * @param skillTierId identifiant Blizzard du skill tier
     * @return identifiants des recettes à synchroniser
     */
    @Query("""
            select distinct association.recipe.id
            from ProfessionSkillTierCategoryRecipeEntity association
            where association.category.skillTier.id = :skillTierId
              and association.recipe.detailSynchronized = false
            order by association.recipe.id
            """)
    List<Integer> findDistinctIncompleteRecipeIdsBySkillTierId(
            @Param("skillTierId") Integer skillTierId
    );

    /**
     * Retourne un lot de recettes distinctes qui n'ont pas
     * encore été synchronisées en détail pour une profession.
     *
     * @param professionId identifiant Blizzard de la profession
     * @param pageable taille maximale du lot
     * @return identifiants des recettes à synchroniser
     */
    @Query("""
            select distinct association.recipe.id
            from ProfessionSkillTierCategoryRecipeEntity association
            where association.category.skillTier.profession.id = :professionId
              and association.recipe.detailSynchronized = false
            order by association.recipe.id
            """)
    List<Integer> findDistinctIncompleteRecipeIdsByProfessionId(
            @Param("professionId") Integer professionId,
            Pageable pageable
    );

    /**
     * Supprime toutes les associations catégorie/recette
     * appartenant à un skill tier.
     *
     * @param skillTierId identifiant Blizzard du skill tier
     */
    @Modifying
    @Query("""
            delete from ProfessionSkillTierCategoryRecipeEntity association
            where association.category.skillTier.id = :skillTierId
            """)
    void deleteAllBySkillTierId(
            @Param("skillTierId") Integer skillTierId
    );
}