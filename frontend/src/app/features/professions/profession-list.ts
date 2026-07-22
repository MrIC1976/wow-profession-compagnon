import {
  ChangeDetectorRef,
  Component,
  OnInit,
  inject
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { ProfessionService } from '../../core/services/profession.service';
import { Profession } from '../../shared/models/profession.model';

@Component({
  selector: 'app-profession-list',
  standalone: true,

  imports: [
    CommonModule,
    MatCardModule,
    MatProgressSpinnerModule
  ],

  templateUrl: './profession-list.html',
  styleUrl: './profession-list.scss'
})
export class ProfessionList implements OnInit {

  private readonly professionService =
    inject(ProfessionService);

  private readonly changeDetectorRef =
    inject(ChangeDetectorRef);

  public professions: Profession[] = [];

  public isLoading = true;

  public errorMessage = '';

  public ngOnInit(): void {
    this.loadProfessions();
  }

  private loadProfessions(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.professionService.getProfessions().subscribe({
      next: (professions: Profession[]) => {
        this.professions = professions;
        this.isLoading = false;
        this.errorMessage = '';

        this.changeDetectorRef.detectChanges();
      },

      error: (error: unknown) => {
        console.error(
          'Erreur pendant le chargement des métiers :',
          error
        );

        this.professions = [];
        this.errorMessage =
          'Impossible de récupérer les métiers.';

        this.isLoading = false;

        this.changeDetectorRef.detectChanges();
      }
    });
  }
}
