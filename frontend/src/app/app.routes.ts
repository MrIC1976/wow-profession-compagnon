/**
 * ============================================================================
 * WoW Profession Compagnon
 * ----------------------------------------------------------------------------
 * Définition des routes principales de l'application.
 *
 * Toutes les pages utilisent le Layout principal.
 * ============================================================================
 */

import { Routes } from '@angular/router';

import { Layout } from './layout/components/layout/layout';
import { Home } from './features/home/pages/home/home';

export const routes: Routes = [
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '',
        component: Home,
        title: 'Accueil'
      }
    ]
  },

  {
    path: '**',
    redirectTo: ''
  }
];