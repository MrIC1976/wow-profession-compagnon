/**
 * ============================================================================
 * Fichier : home.ts
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Ce composant représente la page d'accueil de l'application.
 *
 * Lors de son chargement, il vérifie que le backend Spring Boot
 * est disponible.
 *
 * Il affiche également la liste des métiers récupérés depuis le backend.
 * ============================================================================
 */

import {
  ChangeDetectorRef,
  Component,
  OnInit,
  inject
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { HealthService } from '../../../../core/services/health.service';
import { HealthResponse } from '../../../../shared/models/health-response.model';
import { ProfessionList } from '../../../professions/profession-list';

@Component({
  selector: 'app-home',
  standalone: true,

  imports: [
    CommonModule,
    ProfessionList
  ],

  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home implements OnInit {

  /**
   * Service utilisé pour vérifier l'état du backend.
   */
  private readonly healthService = inject(HealthService);

  /**
   * Permet de déclencher manuellement la mise à jour
   * de l'affichage après la réponse HTTP.
   */
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  /**
   * Réponse retournée par l'endpoint GET /api/health.
   */
  public backend?: HealthResponse;

  /**
   * Indique si le backend est disponible.
   */
  public backendOnline = false;

  /**
   * Message affiché lorsque la connexion au backend échoue.
   */
  public error = '';

  /**
   * Méthode appelée automatiquement lors du chargement du composant.
   */
  public ngOnInit(): void {
    this.checkBackendHealth();
  }

  /**
   * Vérifie que le backend Spring Boot répond correctement.
   */
  private checkBackendHealth(): void {
    this.error = '';

    this.healthService.getHealth().subscribe({
      next: (response: HealthResponse) => {
        console.log('HEALTH OK :', response);

        this.backend = response;
        this.backendOnline = true;
        this.error = '';

        this.changeDetectorRef.detectChanges();
      },

      error: (error: unknown) => {
        console.error(
          'Erreur pendant la vérification du backend :',
          error
        );

        this.backend = undefined;
        this.backendOnline = false;
        this.error = 'Impossible de joindre le backend Spring Boot.';

        this.changeDetectorRef.detectChanges();
      }
    });
  }
}
