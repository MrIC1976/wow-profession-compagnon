package fr.emeric.wowprofessioncompagnon.profession.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Représente un niveau de compétence associé à une profession.
 */
@Entity
@Table(name = "profession_skill_tiers")
public class ProfessionSkillTierEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "profession_id",
            nullable = false
    )
    private ProfessionEntity profession;

    public ProfessionSkillTierEntity() {
    }

    public ProfessionSkillTierEntity(
            Integer id,
            String name,
            ProfessionEntity profession
    ) {
        this.id = id;
        this.name = name;
        this.profession = profession;
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

    public ProfessionEntity getProfession() {
        return profession;
    }

    public void setProfession(ProfessionEntity profession) {
        this.profession = profession;
    }
}