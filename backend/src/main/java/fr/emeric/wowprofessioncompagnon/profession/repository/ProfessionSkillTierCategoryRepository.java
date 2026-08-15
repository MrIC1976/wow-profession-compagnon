package fr.emeric.wowprofessioncompagnon.profession.repository;

import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository permettant d'accéder aux catégories
 * des skill tiers.
 */
@Repository
public interface ProfessionSkillTierCategoryRepository
        extends JpaRepository<ProfessionSkillTierCategoryEntity, Long> {

    /**
     * Retourne toutes les catégories d'un skill tier.
     *
     * @param skillTierId identifiant Blizzard du skill tier
     * @return catégories du skill tier
     */
    @Query("""
            select category
            from ProfessionSkillTierCategoryEntity category
            where category.skillTier.id = :skillTierId
            order by category.id
            """)
    List<ProfessionSkillTierCategoryEntity> findAllBySkillTierId(
            @Param("skillTierId") Integer skillTierId
    );

    /**
     * Supprime toutes les catégories appartenant à un skill tier.
     *
     * @param skillTierId identifiant Blizzard du skill tier
     */
    @Modifying
    @Query("""
            delete from ProfessionSkillTierCategoryEntity category
            where category.skillTier.id = :skillTierId
            """)
    void deleteAllBySkillTierId(
            @Param("skillTierId") Integer skillTierId
    );
}