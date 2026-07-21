/**
 * ============================================================================
 * Fichier : dashboard.ts
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Ce composant représente la page d'accueil de l'application.
 *
 * Lors de son initialisation, il interroge le backend Spring Boot
 * afin de vérifier qu'il est bien disponible.
 *
 * Plus tard, cette page affichera :
 *  - les statistiques générales
 *  - les métiers
 *  - les matériaux
 *  - les recettes
 *  - les dernières mises à jour Blizzard
 * ============================================================================
 */

import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthService } from '../../core/services/health.service';
import { HealthResponse } from '../../shared/models/health-response.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class DashboardComponent implements OnInit {

  /**
   * Service permettant de communiquer avec le backend.
   */
  private readonly healthService = inject(HealthService);

  /**
   * Réponse du backend.
   */
  public backend?: HealthResponse;

  /**
   * Indique si le backend est accessible.
   */
  public connected = false;

  /**
   * Message d'erreur éventuel.
   */
  public errorMessage = '';

  /**
   * Appelé automatiquement lors du chargement du composant.
   */
  ngOnInit(): void {

    this.healthService.getHealth().subscribe({

      next: (response) => {

        this.backend = response;
        this.connected = true;

      },

      error: () => {

        this.connected = false;
        this.errorMessage = 'Impossible de joindre le backend Spring Boot.';

      }

    });

  }

}
