/**
 * ============================================================================
 * WoW Profession Compagnon
 * ----------------------------------------------------------------------------
 * Composant racine de l'application.
 *
 * Ce composant ne fait qu'afficher le RouterOutlet.
 * Toutes les pages passeront par le système de routage Angular.
 * ============================================================================
 */

import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {}