/**
 * ============================================================================
 * Interface représentant la réponse du Backend
 * pour l'endpoint /api/health.
 *
 * Cette interface permet à Angular de connaître
 * exactement la structure des données reçues.
 * ============================================================================
 */

export interface HealthResponse {

  /**
   * Nom de l'application.
   */
  application: string;

  /**
   * Etat du Backend.
   */
  status: string;

  /**
   * Version de l'application.
   */
  version: string;

}
