/**
 * ============================================================================
 * Modèle représentant un métier de World of Warcraft.
 * ============================================================================
 */
export interface Profession {

  /**
   * Identifiant Blizzard.
   */
  id: number;

  /**
   * Nom du métier.
   * Exemple : Forge.
   */
  name: string;

  /**
   * Icône associée.
   */
  icon: string;

  /**
   * Type du métier.
   */
  type: 'PRIMARY' | 'SECONDARY';
}
