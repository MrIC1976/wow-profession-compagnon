package fr.emeric.wowprofessioncompagnon.profession.model;

import java.util.List;

/**
 * Représente une catégorie appartenant à un skill tier
 * dans le domaine métier.
 */
public record ProfessionSkillTierCategory(
        String name,
        List<ProfessionRecipeReference> recipes
) {
}