package fr.emeric.wowprofessioncompagnon.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Représente un objet World of Warcraft stocké
 * dans la base de données.
 */
@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public ItemEntity() {
    }

    public ItemEntity(
            Integer id,
            String name
    ) {
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