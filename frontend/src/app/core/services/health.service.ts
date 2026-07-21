/**
 * ============================================================================
 * Fichier : health.service.ts
 * Projet : WoW Profession Compagnon
 * ----------------------------------------------------------------------------
 * Ce service est responsable de la communication
 * avec l'endpoint :
 *
 * GET /api/health
 *
 * Il sera utilisé par le Dashboard afin de vérifier
 * que le Backend est opérationnel.
 * ============================================================================
 */

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiConfig } from '../config/api.config';
import { HealthResponse } from '../../shared/models/health-response.model';

@Injectable({
  providedIn: 'root'
})
export class HealthService {

  /**
   * Injection du client HTTP Angular.
   */
  private readonly http = inject(HttpClient);

  /**
   * Retourne l'état du Backend.
   */
  public getHealth(): Observable<HealthResponse> {

    return this.http.get<HealthResponse>(
      `${ApiConfig.BASE_URL}/health`
    );

  }

}
