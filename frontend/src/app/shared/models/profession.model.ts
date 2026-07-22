/**
 * ============================================================================
 * Fichier : profession.model.ts
 * Projet : WoW Profession Compagnon
 * ----------------------------------------------------------------------------
 * Représente un métier de World of Warcraft.
 *
 * Cette interface correspond exactement au JSON renvoyé
 * par l'API Spring Boot.
 * ============================================================================
 */

export interface Profession {

  /**
   * Identifiant Blizzard.
   */
  id: number;

  /**
   * Nom du métier.
   */
  name: string;

  /**
   * Icône du métier.
   */
  icon: string;

}
