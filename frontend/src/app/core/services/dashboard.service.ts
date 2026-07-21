import { Injectable } from '@angular/core';

/**
 * ============================================================================
 * DashboardService
 * ----------------------------------------------------------------------------
 * Fournit les statistiques affichées sur le tableau de bord.
 * ============================================================================
 */
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  /**
   * Retourne les statistiques du Dashboard.
   * Pour le moment, les données sont simulées.
   */
  getStats() {

    return {

      professions: 13,

      materials: 5248,

      recipes: 12984

    };

  }

}
