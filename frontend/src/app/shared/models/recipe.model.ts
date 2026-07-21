/**
 * ============================================================================
 * Représente une recette de métier.
 * ============================================================================
 */
export interface Recipe {

  id: number;

  name: string;

  icon: string;

  professionId: number;

  difficulty?: string;
}
