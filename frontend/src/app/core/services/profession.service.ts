import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Profession } from '../../shared/models/profession.model';
import { ApiConfig } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ProfessionService {

  private readonly http = inject(HttpClient);

  public getProfessions(): Observable<Profession[]> {
    return this.http.get<Profession[]>(
      `${ApiConfig.BASE_URL}/professions`
    );
  }
}
