package fr.emeric.wowprofessioncompagnon.profession.entity;

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
 * Représente une catégorie appartenant à un skill tier
 * d'une profession.
 *
 * Blizzard ne fournit pas d'identifiant pour les catégories.
 * L'identifiant PostgreSQL est donc généré localement.
 */
@Entity
@Table(
        name = "profession_skill_tier_categories",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_skill_tier_category",
                        columnNames = {
                                "skill_tier_id",
                                "name"
                        }
                )
        }
)
public class ProfessionSkillTierCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "skill_tier_id",
            nullable = false
    )
    private ProfessionSkillTierEntity skillTier;

    public ProfessionSkillTierCategoryEntity() {
    }

    public ProfessionSkillTierCategoryEntity(
            String name,
            ProfessionSkillTierEntity skillTier
    ) {
        this.name = name;
        this.skillTier = skillTier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessionSkillTierEntity getSkillTier() {
        return skillTier;
    }

    public void setSkillTier(
            ProfessionSkillTierEntity skillTier
    ) {
        this.skillTier = skillTier;
    }
}