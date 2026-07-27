package fr.emeric.wowprofessioncompagnon.profession.mapper;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionDto;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;

import java.util.List;

/**
 * Mapper permettant de convertir les DTO Blizzard en objets métier.
 */
public final class ProfessionMapper {

    private ProfessionMapper() {
        // Classe utilitaire
    }

    /**
     * Convertit une profession Blizzard en objet métier.
     *
     * @param dto profession Blizzard
     * @return profession métier
     */
    public static Profession toDomain(BlizzardProfessionDto dto) {

        return new Profession(
                dto.id(),
                dto.name()
        );
    }

    /**
     * Convertit une liste de professions Blizzard.
     *
     * @param professions liste Blizzard
     * @return liste métier
     */
    public static List<Profession> toDomain(List<BlizzardProfessionDto> professions) {

        return professions.stream()
                .map(ProfessionMapper::toDomain)
                .toList();
    }

}