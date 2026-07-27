package fr.emeric.wowprofessioncompagnon.profession.repository;

import fr.emeric.wowprofessioncompagnon.profession.entity.ProfessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<ProfessionEntity, Integer> {
}