package fr.emeric.wowprofessioncompagnon.recipe.entity;

import fr.emeric.wowprofessioncompagnon.item.entity.ItemEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Représente un composant nécessaire à la fabrication d'une recette.
 */
@Entity
@Table(
        name = "recipe_reagents",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_recipe_reagent",
                        columnNames = {
                                "recipe_id",
                                "reagent_item_id"
                        }
                )
        }
)
public class RecipeReagentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "recipe_id",
            nullable = false
    )
    private RecipeEntity recipe;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "reagent_item_id",
            nullable = false
    )
    private ItemEntity reagentItem;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "recraft_quantity")
    private Integer recraftQuantity;

    public RecipeReagentEntity() {
    }

    public RecipeReagentEntity(
            RecipeEntity recipe,
            ItemEntity reagentItem,
            Integer quantity,
            Integer recraftQuantity
    ) {
        this.recipe = recipe;
        this.reagentItem = reagentItem;
        this.quantity = quantity;
        this.recraftQuantity = recraftQuantity;
    }

    public Long getId() {
        return id;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public ItemEntity getReagentItem() {
        return reagentItem;
    }

    public void setReagentItem(ItemEntity reagentItem) {
        this.reagentItem = reagentItem;
    }

    /**
     * Getter de compatibilité pour le modèle métier actuel.
     *
     * @return identifiant Blizzard du composant
     */
    public Integer getReagentItemId() {
        return reagentItem.getId();
    }

    /**
     * Getter de compatibilité pour le modèle métier actuel.
     *
     * @return nom du composant
     */
    public String getReagentName() {
        return reagentItem.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRecraftQuantity() {
        return recraftQuantity;
    }

    public void setRecraftQuantity(Integer recraftQuantity) {
        this.recraftQuantity = recraftQuantity;
    }
}