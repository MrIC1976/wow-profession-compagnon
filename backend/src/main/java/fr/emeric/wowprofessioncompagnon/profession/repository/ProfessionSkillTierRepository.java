package fr.emeric.wowprofessioncompagnon.profession.repository;

import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository permettant d'accéder aux niveaux de compétence
 * associés aux professions.
 */
@Repository
public interface ProfessionSkillTierRepository
        extends JpaRepository<ProfessionSkillTierEntity, Integer> {

    /**
     * Retourne les skill tiers associés à une profession.
     *
     * @param professionId identifiant Blizzard de la profession
     * @return skill tiers de la profession
     */
    @Query("""
            select skillTier
            from ProfessionSkillTierEntity skillTier
            where skillTier.profession.id = :professionId
            order by skillTier.id
            """)
    List<ProfessionSkillTierEntity> findAllByProfessionId(
            @Param("professionId") Integer professionId
    );

    /**
     * Retourne un skill tier appartenant à une profession précise.
     *
     * @param professionId identifiant Blizzard de la profession
     * @param skillTierId identifiant Blizzard du skill tier
     * @return skill tier s'il existe pour cette profession
     */
    @Query("""
            select skillTier
            from ProfessionSkillTierEntity skillTier
            where skillTier.id = :skillTierId
              and skillTier.profession.id = :professionId
            """)
    Optional<ProfessionSkillTierEntity> findByProfessionIdAndSkillTierId(
            @Param("professionId") Integer professionId,
            @Param("skillTierId") Integer skillTierId
    );
}