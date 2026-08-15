package fr.emeric.wowprofessioncompagnon.profession.synchronization;

import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardCategoryDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionDetailDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionSkillTierDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardProfessionTypeDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardRecipeReferenceDto;
import fr.emeric.wowprofessioncompagnon.blizzard.dto.BlizzardSkillTierDto;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardProfessionDetailService;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardProfessionService;
import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardProfessionSkillTierService;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierCategoryRecipeEntity;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionSkillTierEntity;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierCategoryRecipeRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierCategoryRepository;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionSkillTierRepository;
import fr.emeric.wowprofessioncompagnon.recipe.entity.RecipeEntity;
import fr.emeric.wowprofessioncompagnon.recipe.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service permettant de synchroniser les professions,
 * leurs skill tiers, leurs catégories et leurs références
 * de recettes depuis Blizzard vers PostgreSQL.
 */
@Service
public class ProfessionSynchronizationService {

    private final BlizzardProfessionService blizzardProfessionService;
    private final BlizzardProfessionDetailService blizzardProfessionDetailService;
    private final BlizzardProfessionSkillTierService blizzardProfessionSkillTierService;

    private final ProfessionRepository professionRepository;
    private final ProfessionSkillTierRepository professionSkillTierRepository;
    private final ProfessionSkillTierCategoryRepository categoryRepository;
    private final ProfessionSkillTierCategoryRecipeRepository categoryRecipeRepository;
    private final RecipeRepository recipeRepository;

    public ProfessionSynchronizationService(
            BlizzardProfessionService blizzardProfessionService,
            BlizzardProfessionDetailService blizzardProfessionDetailService,
            BlizzardProfessionSkillTierService blizzardProfessionSkillTierService,
            ProfessionRepository professionRepository,
            ProfessionSkillTierRepository professionSkillTierRepository,
            ProfessionSkillTierCategoryRepository categoryRepository,
            ProfessionSkillTierCategoryRecipeRepository categoryRecipeRepository,
            RecipeRepository recipeRepository
    ) {
        this.blizzardProfessionService = blizzardProfessionService;
        this.blizzardProfessionDetailService = blizzardProfessionDetailService;
        this.blizzardProfessionSkillTierService = blizzardProfessionSkillTierService;
        this.professionRepository = professionRepository;
        this.professionSkillTierRepository = professionSkillTierRepository;
        this.categoryRepository = categoryRepository;
        this.categoryRecipeRepository = categoryRecipeRepository;
        this.recipeRepository = recipeRepository;
    }

    /**
     * Synchronise toutes les professions Blizzard,
     * leurs détails et leurs références de skill tiers.
     *
     * @return professions synchronisées
     */
    @Transactional
    public List<ProfessionEntity> synchronizeProfessions() {

        List<Profession> professions =
                blizzardProfessionService.getProfessions();

        List<ProfessionEntity> synchronizedProfessions =
                new ArrayList<>();

        for (Profession profession : professions) {

            ProfessionEntity synchronizedProfession =
                    synchronizeProfessionDetail(
                            profession.id()
                    );

            if (synchronizedProfession != null) {
                synchronizedProfessions.add(
                        synchronizedProfession
                );
            }
        }

        return synchronizedProfessions;
    }

    /**
     * Synchronise le détail et les références de skill tiers
     * d'une seule profession.
     *
     * @param professionId identifiant Blizzard de la profession
     * @return profession synchronisée ou null
     */
    @Transactional
    public ProfessionEntity synchronizeProfession(
            int professionId
    ) {
        return synchronizeProfessionDetail(
                professionId
        );
    }

    /**
     * Synchronise le contenu complet d'un skill tier :
     * catégories et références de recettes.
     *
     * @param professionId identifiant Blizzard de la profession
     * @param skillTierId identifiant Blizzard du skill tier
     * @return nombre de catégories synchronisées
     */
    @Transactional
    public int synchronizeSkillTier(
            int professionId,
            int skillTierId
    ) {
        ProfessionEntity profession =
                professionRepository.findById(professionId)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Profession inconnue : " + professionId
                                )
                        );

        ProfessionSkillTierEntity skillTier =
                professionSkillTierRepository.findById(skillTierId)
                        .orElseGet(
                                () -> new ProfessionSkillTierEntity(
                                        skillTierId,
                                        "Skill tier " + skillTierId,
                                        profession
                                )
                        );

        BlizzardProfessionSkillTierDto detail =
                blizzardProfessionSkillTierService.getSkillTier(
                        professionId,
                        skillTierId
                );

        if (detail == null) {
            return 0;
        }

        skillTier.setName(detail.name());
        skillTier.setProfession(profession);

        ProfessionSkillTierEntity savedSkillTier =
                professionSkillTierRepository.save(skillTier);

        categoryRecipeRepository.deleteAllBySkillTierId(
                skillTierId
        );

        categoryRepository.deleteAllBySkillTierId(
                skillTierId
        );

        if (detail.categories() == null
                || detail.categories().isEmpty()) {
            return 0;
        }

        int categoryCount = 0;

        for (BlizzardCategoryDto category : detail.categories()) {

            if (category == null || category.name() == null) {
                continue;
            }

            ProfessionSkillTierCategoryEntity categoryEntity =
                    categoryRepository.save(
                            new ProfessionSkillTierCategoryEntity(
                                    category.name(),
                                    savedSkillTier
                            )
                    );

            synchronizeCategoryRecipes(
                    categoryEntity,
                    category.recipes()
            );

            categoryCount++;
        }

        return categoryCount;
    }

    /**
     * Récupère et enregistre le détail d'une profession,
     * puis synchronise les références de ses skill tiers.
     *
     * @param professionId identifiant Blizzard
     * @return profession persistée ou null
     */
    private ProfessionEntity synchronizeProfessionDetail(
            int professionId
    ) {
        BlizzardProfessionDetailDto detail =
                blizzardProfessionDetailService
                        .getProfessionDetail(professionId);

        if (detail == null) {
            return null;
        }

        BlizzardProfessionTypeDto type =
                detail.type();

        ProfessionEntity professionEntity =
                new ProfessionEntity(
                        detail.id(),
                        detail.name(),
                        detail.description(),
                        type != null ? type.type() : null,
                        type != null ? type.name() : null
                );

        ProfessionEntity savedProfession =
                professionRepository.save(professionEntity);

        synchronizeSkillTierReferences(
                savedProfession,
                detail.skillTiers()
        );

        return savedProfession;
    }

    /**
     * Synchronise les références de skill tiers
     * d'une profession.
     */
    private void synchronizeSkillTierReferences(
            ProfessionEntity profession,
            List<BlizzardSkillTierDto> skillTiers
    ) {
        if (skillTiers == null || skillTiers.isEmpty()) {
            return;
        }

        List<ProfessionSkillTierEntity> entities =
                skillTiers.stream()
                        .map(skillTier ->
                                new ProfessionSkillTierEntity(
                                        skillTier.id(),
                                        skillTier.name(),
                                        profession
                                )
                        )
                        .toList();

        professionSkillTierRepository.saveAll(entities);
    }

    /**
     * Synchronise les références de recettes d'une catégorie.
     *
     * Une recette déjà détaillée n'est pas écrasée.
     * Une recette inexistante est créée avec son id et son nom.
     */
    private void synchronizeCategoryRecipes(
            ProfessionSkillTierCategoryEntity category,
            List<BlizzardRecipeReferenceDto> recipes
    ) {
        if (recipes == null || recipes.isEmpty()) {
            return;
        }

        for (BlizzardRecipeReferenceDto recipeReference : recipes) {

            if (recipeReference == null) {
                continue;
            }

            RecipeEntity recipe =
                    recipeRepository.findById(recipeReference.id())
                            .orElseGet(
                                    () -> recipeRepository.save(
                                            new RecipeEntity(
                                                    recipeReference.id(),
                                                    recipeReference.name()
                                            )
                                    )
                            );

            ProfessionSkillTierCategoryRecipeEntity association =
                    new ProfessionSkillTierCategoryRecipeEntity(
                            category,
                            recipe
                    );

            categoryRecipeRepository.save(association);
        }
    }
}