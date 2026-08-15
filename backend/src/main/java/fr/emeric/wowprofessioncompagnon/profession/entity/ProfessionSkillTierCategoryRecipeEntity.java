package fr.emeric.wowprofessioncompagnon.profession.entity;

import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;

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
 * Représente l'association entre une catégorie
 * d'un skill tier et une recette.
 */
@Entity
@Table(
        name = "profession_skill_tier_category_recipes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_category_recipe",
                        columnNames = {
                                "category_id",
                                "recipe_id"
                        }
                )
        }
)
public class ProfessionSkillTierCategoryRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private ProfessionSkillTierCategoryEntity category;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "recipe_id",
            nullable = false
    )
    private RecipeEntity recipe;

    public ProfessionSkillTierCategoryRecipeEntity() {
    }

    public ProfessionSkillTierCategoryRecipeEntity(
            ProfessionSkillTierCategoryEntity category,
            RecipeEntity recipe
    ) {
        this.category = category;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public ProfessionSkillTierCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(
            ProfessionSkillTierCategoryEntity category
    ) {
        this.category = category;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(
            RecipeEntity recipe
    ) {
        this.recipe = recipe;
    }
}