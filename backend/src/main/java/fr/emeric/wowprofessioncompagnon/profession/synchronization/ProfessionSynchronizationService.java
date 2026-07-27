package fr.emeric.wowprofessioncompagnon.profession.synchronization;

import fr.emeric.wowprofessioncompagnon.blizzard.service.BlizzardProfessionService;
import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionEntity;
import fr.emeric.wowprofessioncompagnon.profession.model.Profession;
import fr.emeric.wowprofessioncompagnon.profession.repository.ProfessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessionSynchronizationService {

    private final BlizzardProfessionService blizzardProfessionService;
    private final ProfessionRepository professionRepository;

    public ProfessionSynchronizationService(
            BlizzardProfessionService blizzardProfessionService,
            ProfessionRepository professionRepository
    ) {
        this.blizzardProfessionService = blizzardProfessionService;
        this.professionRepository = professionRepository;
    }

    @Transactional
    public List<ProfessionEntity> synchronizeProfessions() {
        List<Profession> professions = blizzardProfessionService.getProfessions();

        List<ProfessionEntity> entities = professions.stream()
                .map(profession -> new ProfessionEntity(
                        profession.id(),
                        profession.name()
                ))
                .toList();

        return professionRepository.saveAll(entities);
    }
}