package fr.emeric.wowprofessioncompagnon.profession.model;

import java.util.List;

/**
 * Représente une profession dans le domaine métier de l'application.
 */
public record Profession(
        int id,
        String name,
        String description,
        String type,
        String typeName,
        List<ProfessionSkillTier> skillTiers
) {
}