package fr.emeric.wowprofessioncompagnon.recipe.entity;

import fr.emeric.wowprofessioncompagnon.item.entity.ItemEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Représente une recette stockée dans la base de données.
 */
@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(name = "crafted_quantity")
    private Double craftedQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alliance_crafted_item_id")
    private ItemEntity allianceCraftedItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horde_crafted_item_id")
    private ItemEntity hordeCraftedItem;

    public RecipeEntity() {
    }

    public RecipeEntity(
            Integer id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }

    public RecipeEntity(
            Integer id,
            String name,
            String description,
            Double craftedQuantity,
            ItemEntity allianceCraftedItem,
            ItemEntity hordeCraftedItem
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.craftedQuantity = craftedQuantity;
        this.allianceCraftedItem = allianceCraftedItem;
        this.hordeCraftedItem = hordeCraftedItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCraftedQuantity() {
        return craftedQuantity;
    }

    public void setCraftedQuantity(Double craftedQuantity) {
        this.craftedQuantity = craftedQuantity;
    }

    public ItemEntity getAllianceCraftedItem() {
        return allianceCraftedItem;
    }

    public void setAllianceCraftedItem(
            ItemEntity allianceCraftedItem
    ) {
        this.allianceCraftedItem = allianceCraftedItem;
    }

    public ItemEntity getHordeCraftedItem() {
        return hordeCraftedItem;
    }

    public void setHordeCraftedItem(
            ItemEntity hordeCraftedItem
    ) {
        this.hordeCraftedItem = hordeCraftedItem;
    }

    /**
     * Getter de compatibilité utilisé par le modèle métier actuel.
     *
     * @return identifiant de l'objet Alliance ou null
     */
    public Integer getAllianceCraftedItemId() {
        if (allianceCraftedItem == null) {
            return null;
        }

        return allianceCraftedItem.getId();
    }

    /**
     * Getter de compatibilité utilisé par le modèle métier actuel.
     *
     * @return identifiant de l'objet Horde ou null
     */
    public Integer getHordeCraftedItemId() {
        if (hordeCraftedItem == null) {
            return null;
        }

        return hordeCraftedItem.getId();
    }
}