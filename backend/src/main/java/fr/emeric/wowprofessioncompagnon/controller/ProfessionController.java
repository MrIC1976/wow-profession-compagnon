package fr.emeric.wowprofessioncompagnon.controller;

import fr.emeric.wowprofessioncompagnon.dto.ProfessionResponse;
import fr.emeric.wowprofessioncompagnon.service.ProfessionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST responsable des métiers de World of Warcraft.
 *
 * L'annotation CrossOrigin autorise le frontend Angular,
 * exécuté sur http://localhost:4200, à appeler les endpoints
 * présents dans ce contrôleur.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessionController {

    /**
     * Service contenant la logique métier liée aux professions.
     */
    private final ProfessionService professionService;

    /**
     * Constructeur utilisé par Spring pour injecter ProfessionService.
     *
     * @param professionService service des métiers
     */
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    /**
     * Retourne l'ensemble des métiers disponibles.
     *
     * URL :
     * GET http://localhost:8080/api/professions
     *
     * @return liste des métiers
     */
    @GetMapping("/api/professions")
    public List<ProfessionResponse> getAllProfessions() {
        return professionService.getAllProfessions();
    }
}