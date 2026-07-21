/**
 * ============================================================================
 * Fichier : home.ts
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Ce composant représente la page d'accueil de l'application.
 *
 * Lors de son chargement, il vérifie que le Backend Spring Boot
 * est disponible.
 *
 * Cette page deviendra progressivement le véritable tableau de bord
 * de l'application.
 * ============================================================================
 */

import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthService } from '../../../../core/services/health.service';
import { HealthResponse } from '../../../../shared/models/health-response.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home implements OnInit {

  /**
   * Service de communication avec le backend.
   */
  private readonly healthService = inject(HealthService);

  /**
   * Réponse du backend.
   */
  public backend?: HealthResponse;

  /**
   * Indique si le backend est disponible.
   */
  public backendOnline = false;

  /**
   * Message d'erreur.
   */
  public error = '';

  /**
   * Chargement du composant.
   */
  ngOnInit(): void {

    this.healthService.getHealth().subscribe({

      next: (response) => {

        this.backend = response;
        this.backendOnline = true;

      },

      error: () => {

        this.backendOnline = false;
        this.error = 'Impossible de joindre le backend Spring Boot.';

      }

    });

  }

}
