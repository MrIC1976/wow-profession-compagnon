import { Injectable } from '@angular/core';

/**
 * ============================================================================
 * BlizzardApiService
 * ----------------------------------------------------------------------------
 * Ce service sera responsable de tous les appels HTTP vers notre backend.
 *
 * IMPORTANT :
 * Angular n'appellera JAMAIS directement Blizzard.
 *
 * Architecture :
 *
 * Angular
 *    ↓
 * Spring Boot
 *    ↓
 * Blizzard OAuth
 *    ↓
 * Blizzard API
 * ============================================================================
 */
@Injectable({
  providedIn: 'root'
})
export class BlizzardApiService {

}
