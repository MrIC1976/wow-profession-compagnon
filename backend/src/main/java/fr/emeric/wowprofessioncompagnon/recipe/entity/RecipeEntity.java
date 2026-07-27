package fr.emeric.wowprofessioncompagnon.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public RecipeEntity() {
    }

    public RecipeEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}