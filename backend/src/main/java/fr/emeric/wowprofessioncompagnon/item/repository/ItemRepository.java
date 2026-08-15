package fr.emeric.wowprofessioncompagnon.item.repository;

import fr.emeric.wowprofessioncompagnon.item.entity.ItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository permettant d'accéder aux objets
 * stockés dans PostgreSQL.
 */
@Repository
public interface ItemRepository
        extends JpaRepository<ItemEntity, Integer> {
}