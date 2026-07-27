package fr.emeric.wowprofessioncompagnon.common.exception;

import java.time.LocalDateTime;

/**
 * Représente une réponse d'erreur standard de l'API.
 *
 * @param timestamp date et heure de l'erreur
 * @param status code HTTP
 * @param error code fonctionnel de l'erreur
 * @param message message explicite
 * @param path chemin de la requête
 */
public record ApiError(

        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path

) {
}