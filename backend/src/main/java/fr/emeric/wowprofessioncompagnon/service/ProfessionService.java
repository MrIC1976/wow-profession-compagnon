package fr.emeric.wowprofessioncompagnon.service;

import fr.emeric.wowprofessioncompagnon.dto.ProfessionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ============================================================================
 * Fichier : ProfessionService.java
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Cette classe contient toute la logique métier concernant
 * les métiers de World of Warcraft.
 *
 * Pour cette première version, les données sont écrites en dur.
 *
 * Plus tard, cette classe appellera directement l'API Blizzard
 * afin de récupérer les métiers en temps réel.
 * ============================================================================
 */
@Service
public class ProfessionService {

    /**
     * Retourne la liste des métiers principaux.
     *
     * @return Liste des métiers.
     */
    public List<ProfessionResponse> getAllProfessions() {

        return List.of(

                new ProfessionResponse(
                        164,
                        "Forge",
                        "assets/icons/blacksmithing.png"
                ),

                new ProfessionResponse(
                        165,
                        "Travail du cuir",
                        "assets/icons/leatherworking.png"
                ),

                new ProfessionResponse(
                        171,
                        "Alchimie",
                        "assets/icons/alchemy.png"
                ),

                new ProfessionResponse(
                        182,
                        "Herboristerie",
                        "assets/icons/herbalism.png"
                ),

                new ProfessionResponse(
                        186,
                        "Minage",
                        "assets/icons/mining.png"
                ),

                new ProfessionResponse(
                        197,
                        "Couture",
                        "assets/icons/tailoring.png"
                ),

                new ProfessionResponse(
                        202,
                        "Ingénierie",
                        "assets/icons/engineering.png"
                ),

                new ProfessionResponse(
                        333,
                        "Enchantement",
                        "assets/icons/enchanting.png"
                ),

                new ProfessionResponse(
                        393,
                        "Dépeçage",
                        "assets/icons/skinning.png"
                ),

                new ProfessionResponse(
                        755,
                        "Joaillerie",
                        "assets/icons/jewelcrafting.png"
                ),

                new ProfessionResponse(
                        773,
                        "Calligraphie",
                        "assets/icons/inscription.png"
                )

        );

    }

}