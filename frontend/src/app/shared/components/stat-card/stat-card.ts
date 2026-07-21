import { Component, input } from '@angular/core';

/**
 * ============================================================================
 * StatCard
 * ----------------------------------------------------------------------------
 * Carte réutilisable affichant une statistique.
 *
 * Exemple :
 *
 * <app-stat-card
 *    title="Métiers"
 *    value="13"
 *    icon="construction">
 * </app-stat-card>
 *
 * ============================================================================
 */

@Component({
  selector: 'app-stat-card',
  standalone: true,
  imports: [],
  templateUrl: './stat-card.html',
  styleUrl: './stat-card.scss'
})
export class StatCard {

  /**
   * Titre de la statistique.
   */
  title = input.required<string>();

  /**
   * Valeur affichée.
   */
  value = input.required<string>();

  /**
   * Nom de l'icône Material Symbols.
   */
  icon = input.required<string>();

}
