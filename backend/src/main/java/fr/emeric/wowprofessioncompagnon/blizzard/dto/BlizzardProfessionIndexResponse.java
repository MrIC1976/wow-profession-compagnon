package fr.emeric.wowprofessioncompagnon.blizzard.dto;

import java.util.List;

/**
 * Réponse de l'API Blizzard contenant l'index des professions.
 */
public record BlizzardProfessionIndexResponse(

        List<BlizzardProfessionDto> professions

) {
}