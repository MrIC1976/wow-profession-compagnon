/**
 * ============================================================================
 * Matériau récoltable ou fabriqué.
 * ============================================================================
 */
export interface Material {

  id: number;

  name: string;

  icon: string;

  quality?: number;
}
