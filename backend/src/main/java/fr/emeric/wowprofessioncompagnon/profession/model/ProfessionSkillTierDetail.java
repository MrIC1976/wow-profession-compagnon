package fr.emeric.wowprofessioncompagnon.profession.model;

import java.util.List;

/**
 * Représente le détail complet d'un skill tier
 * lu depuis PostgreSQL.
 */
public record ProfessionSkillTierDetail(
        int id,
        String name,
        List<ProfessionSkillTierCategory> categories
) {
}