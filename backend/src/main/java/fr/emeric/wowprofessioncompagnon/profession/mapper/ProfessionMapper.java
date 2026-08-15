package fr.emeric.wowprofessioncompagnon.profession.mapper;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionDto;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierEntity;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.model.ProfessionSkillTier;

import java.util.List;

/**
 * Mapper permettant de convertir les différentes représentations
 * d'une profession en objets métier.
 */
public final class ProfessionMapper {

    private ProfessionMapper() {
        // Classe utilitaire
    }

    /**
     * Convertit une profession Blizzard simplifiée
     * en objet métier.
     *
     * @param dto profession Blizzard
     * @return profession métier
     */
    public static Profession toDomain(
            BlizzardProfessionDto dto
    ) {
        return new Profession(
                dto.id(),
                dto.name(),
                null,
                null,
                null,
                List.of()
        );
    }

    /**
     * Convertit une profession persistée en objet métier.
     *
     * Les skill tiers ne sont pas chargés dans cette version.
     *
     * @param entity profession stockée en base
     * @return profession métier
     */
    public static Profession toDomain(
            ProfessionEntity entity
    ) {
        return new Profession(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getProfessionType(),
                entity.getProfessionTypeName(),
                List.of()
        );
    }

    /**
     * Convertit une profession persistée et ses skill tiers
     * en objet métier complet.
     *
     * @param entity profession stockée en base
     * @param skillTiers niveaux de compétence stockés en base
     * @return profession métier complète
     */
    public static Profession toDomain(
            ProfessionEntity entity,
            List<ProfessionSkillTierEntity> skillTiers
    ) {
        List<ProfessionSkillTier> domainSkillTiers =
                skillTiers.stream()
                        .map(ProfessionMapper::toDomain)
                        .toList();

        return new Profession(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getProfessionType(),
                entity.getProfessionTypeName(),
                domainSkillTiers
        );
    }

    /**
     * Convertit un skill tier persisté en objet métier.
     *
     * @param entity skill tier stocké
     * @return skill tier métier
     */
    public static ProfessionSkillTier toDomain(
            ProfessionSkillTierEntity entity
    ) {
        return new ProfessionSkillTier(
                entity.getId(),
                entity.getName()
        );
    }

    /**
     * Convertit une liste de professions Blizzard.
     *
     * @param professions liste Blizzard
     * @return liste métier
     */
    public static List<Profession> toDomain(
            List<BlizzardProfessionDto> professions
    ) {
        return professions.stream()
                .map(ProfessionMapper::toDomain)
                .toList();
    }
}