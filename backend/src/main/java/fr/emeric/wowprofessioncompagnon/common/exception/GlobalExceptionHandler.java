package fr.emeric.wowprofessioncompagnon.common.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Gestionnaire global des exceptions de l'application.
 *
 * Il transforme les exceptions métier en réponses HTTP JSON homogènes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les erreurs de communication avec l'API Blizzard.
     *
     * @param exception exception Blizzard
     * @param request requête HTTP en cours
     * @return réponse d'erreur HTTP 503
     */
    @ExceptionHandler(BlizzardApiException.class)
    public ResponseEntity<ApiError> handleBlizzardApiException(
            BlizzardApiException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                status.value(),
                "BLIZZARD_API_UNAVAILABLE",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(status)
                .body(apiError);
    }

    /**
     * Gère les erreurs inattendues qui ne possèdent pas encore
     * de traitement spécifique.
     *
     * @param exception exception inattendue
     * @param request requête HTTP en cours
     * @return réponse d'erreur HTTP 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                status.value(),
                "INTERNAL_SERVER_ERROR",
                "Une erreur interne inattendue est survenue.",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(status)
                .body(apiError);
    }

}