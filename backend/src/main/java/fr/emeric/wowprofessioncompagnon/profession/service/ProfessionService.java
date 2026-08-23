package fr.emeric.wowprofessioncompagnon.profession.service;

import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryRecipeEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierEntity;
import fr.emeric.wowprofessioncompagnon.profession.mapper.ProfessionMapper;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.model.ProfessionRecipeReference;
import fr.emeric.wowprofessioncompagnon.profession.model.ProfessionSkillTierCategory;
import fr.emeric.wowprofessioncompagnon.profession.model.ProfessionSkillTierDetail;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierCategoryRecipeRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierCategoryRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierRepository;
import fr.emeric.wowprofessioncompagnon.profession.synchronization.ProfessionSynchronizationService;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service métier des professions.
 *
 * Les données métier sont lues depuis PostgreSQL.
 * Blizzard est utilisé pour les opérations de synchronisation.
 */
@Service
public class ProfessionService {

    private final ProfessionRepository professionRepository;
    private final ProfessionSkillTierRepository professionSkillTierRepository;
    private final ProfessionSkillTierCategoryRepository categoryRepository;
    private final ProfessionSkillTierCategoryRecipeRepository categoryRecipeRepository;
    private final ProfessionSynchronizationService professionSynchronizationService;

    public ProfessionService(
            ProfessionRepository professionRepository,
            ProfessionSkillTierRepository professionSkillTierRepository,
            ProfessionSkillTierCategoryRepository categoryRepository,
            ProfessionSkillTierCategoryRecipeRepository categoryRecipeRepository,
            ProfessionSynchronizationService professionSynchronizationService
    ) {
        this.professionRepository = professionRepository;
        this.professionSkillTierRepository = professionSkillTierRepository;
        this.categoryRepository = categoryRepository;
        this.categoryRecipeRepository = categoryRecipeRepository;
        this.professionSynchronizationService = professionSynchronizationService;
    }

    public List<Profession> getProfessions() {
        return professionRepository
                .findAll()
                .stream()
                .map(ProfessionMapper::toDomain)
                .toList();
    }

    public Optional<Profession> getProfessionDetail(
            int professionId
    ) {
        Optional<ProfessionEntity> profession =
                professionRepository.findById(professionId);

        if (profession.isEmpty()) {
            return Optional.empty();
        }

        List<ProfessionSkillTierEntity> skillTiers =
                professionSkillTierRepository
                        .findAllByProfessionId(professionId);

        return Optional.of(
                ProfessionMapper.toDomain(
                        profession.get(),
                        skillTiers
                )
        );
    }

    public Optional<ProfessionSkillTierDetail> getSkillTier(
            int professionId,
            int skillTierId
    ) {
        Optional<ProfessionSkillTierEntity> skillTier =
                professionSkillTierRepository
                        .findByProfessionIdAndSkillTierId(
                                professionId,
                                skillTierId
                        );

        if (skillTier.isEmpty()) {
            return Optional.empty();
        }

        List<ProfessionSkillTierCategoryEntity> categories =
                categoryRepository.findAllBySkillTierId(
                        skillTierId
                );

        List<ProfessionSkillTierCategory> domainCategories =
                new ArrayList<>();

        for (ProfessionSkillTierCategoryEntity category : categories) {

            List<ProfessionSkillTierCategoryRecipeEntity> associations =
                    categoryRecipeRepository.findAllByCategoryId(
                            category.getId()
                    );

            List<ProfessionRecipeReference> recipes =
                    associations.stream()
                            .map(association -> {
                                RecipeEntity recipe =
                                        association.getRecipe();

                                return new ProfessionRecipeReference(
                                        recipe.getId(),
                                        recipe.getName()
                                );
                            })
                            .toList();

            domainCategories.add(
                    new ProfessionSkillTierCategory(
                            category.getName(),
                            recipes
                    )
            );
        }

        return Optional.of(
                new ProfessionSkillTierDetail(
                        skillTier.get().getId(),
                        skillTier.get().getName(),
                        domainCategories
                )
        );
    }

    public int synchronizeProfessions() {
        return professionSynchronizationService
                .synchronizeProfessions()
                .size();
    }

    public int synchronizeSkillTier(
            int professionId,
            int skillTierId
    ) {
        return professionSynchronizationService
                .synchronizeSkillTier(
                        professionId,
                        skillTierId
                );
    }

    public int synchronizeAllSkillTiers(
            int professionId
    ) {
        List<ProfessionSkillTierEntity> skillTiers =
                professionSkillTierRepository
                        .findAllByProfessionId(professionId);

        int categoryCount = 0;

        for (ProfessionSkillTierEntity skillTier : skillTiers) {
            categoryCount +=
                    professionSynchronizationService
                            .synchronizeSkillTier(
                                    professionId,
                                    skillTier.getId()
                            );
        }

        return categoryCount;
    }

    public int synchronizeProfession(
            int professionId
    ) {
        ProfessionEntity profession =
                professionSynchronizationService
                        .synchronizeProfession(professionId);

        if (profession == null) {
            return 0;
        }

        return synchronizeAllSkillTiers(
                professionId
        );
    }

    public int synchronizeSkillTierRecipes(
            int professionId,
            int skillTierId
    ) {
        return professionSynchronizationService
                .synchronizeRecipesForSkillTier(
                        professionId,
                        skillTierId
                );
    }

    /**
     * Synchronise un lot de recettes incomplètes
     * pour une profession.
     *
     * @param professionId identifiant Blizzard
     * @param limit taille maximale du lot
     * @return nombre de recettes synchronisées
     */
    public int synchronizeProfessionRecipes(
            int professionId,
            int limit
    ) {
        return professionSynchronizationService
                .synchronizeRecipeBatchForProfession(
                        professionId,
                        limit
                );
    }
}