package fr.emeric.wowprofessioncompagnon.common.dto;

/**
 * Réponse retournée après une synchronisation.
 *
 * @param count nombre d'éléments synchronisés
 */
public record SynchronizationResponse(
        int count
) {
}