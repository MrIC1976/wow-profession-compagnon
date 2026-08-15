package fr.emeric.wowprofessioncompagnon.profession.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Représente une profession stockée dans PostgreSQL.
 */
@Entity
@Table(name = "profession")
public class ProfessionEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(name = "profession_type")
    private String professionType;

    @Column(name = "profession_type_name")
    private String professionTypeName;

    public ProfessionEntity() {
    }

    public ProfessionEntity(
            Integer id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }

    public ProfessionEntity(
            Integer id,
            String name,
            String description,
            String professionType,
            String professionTypeName
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.professionType = professionType;
        this.professionTypeName = professionTypeName;
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

    public String getProfessionType() {
        return professionType;
    }

    public void setProfessionType(String professionType) {
        this.professionType = professionType;
    }

    public String getProfessionTypeName() {
        return professionTypeName;
    }

    public void setProfessionTypeName(String professionTypeName) {
        this.professionTypeName = professionTypeName;
    }
}