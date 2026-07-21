/**
 * ============================================================================
 * Fichier : app.config.ts
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * Ce fichier configure l'application Angular.
 *
 * Il permet d'enregistrer tous les services "globaux"
 * nécessaires au fonctionnement de l'application.
 *
 * Pour le moment nous enregistrons :
 *
 * - Le Router Angular
 * - Le HttpClient pour communiquer avec Spring Boot
 *
 * Dans les prochains chapitres nous ajouterons également :
 *
 * - Les Interceptors HTTP
 * - Le système d'authentification Blizzard
 * - Les animations Angular Material
 * - Le cache
 *
 * ============================================================================
 */

import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {

  providers: [

    /**
     * Gestion globale des erreurs Angular.
     */
    provideBrowserGlobalErrorListeners(),

    /**
     * Configuration du Router.
     */
    provideRouter(routes),

    /**
     * Active HttpClient dans toute l'application.
     *
     * Sans cette ligne il est impossible d'appeler
     * notre backend Spring Boot.
     */
    provideHttpClient()

  ]

};
