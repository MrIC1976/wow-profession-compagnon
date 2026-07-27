package fr.emeric.wowprofessioncompagnon.common.exception;

/**
 * Exception levée lorsqu'une erreur survient lors d'un appel
 * à l'API Blizzard.
 */
public class BlizzardApiException extends RuntimeException {

    public BlizzardApiException(String message) {
        super(message);
    }

    public BlizzardApiException(String message, Throwable cause) {
        super(message, cause);
    }

}