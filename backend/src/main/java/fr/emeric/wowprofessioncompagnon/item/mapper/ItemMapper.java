package fr.emeric.wowprofessioncompagnon.item.mapper;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardItemReferenceDto;
import fr.emeric.wowprofessioncompagnon.item.entity.ItemEntity;
import fr.emeric.wowprofessioncompagnon.item.model.Item;

/**
 * Mapper permettant de convertir les différentes
 * représentations d'un objet World of Warcraft.
 */
public final class ItemMapper {

    private ItemMapper() {
        // Classe utilitaire
    }

    /**
     * Convertit une référence Blizzard en objet métier.
     *
     * @param dto objet Blizzard
     * @return objet métier
     */
    public static Item toDomain(
            BlizzardItemReferenceDto dto
    ) {
        if (dto == null) {
            return null;
        }

        return new Item(
                dto.id(),
                dto.name()
        );
    }

    /**
     * Convertit une entité persistée en objet métier.
     *
     * @param entity objet stocké en base
     * @return objet métier
     */
    public static Item toDomain(
            ItemEntity entity
    ) {
        if (entity == null) {
            return null;
        }

        return new Item(
                entity.getId(),
                entity.getName()
        );
    }

    /**
     * Convertit une référence Blizzard en entité persistable.
     *
     * Le DTO doit posséder un nom exploitable.
     * La gestion des références Blizzard sans nom
     * est effectuée par le service de synchronisation.
     *
     * @param dto objet Blizzard
     * @return entité
     */
    public static ItemEntity toEntity(
            BlizzardItemReferenceDto dto
    ) {
        if (dto == null) {
            return null;
        }

        return new ItemEntity(
                dto.id(),
                dto.name()
        );
    }
}