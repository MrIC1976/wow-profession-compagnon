import { Injectable } from '@angular/core';
import { Profession } from '../../shared/models/profession.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessionService {

  getAll(): Profession[] {

    return [

      {
        id: 164,
        name: 'Forge',
        icon: 'construction',
        type: 'PRIMARY'
      },

      {
        id: 165,
        name: 'Travail du cuir',
        icon: 'checkroom',
        type: 'PRIMARY'
      },

      {
        id: 171,
        name: 'Alchimie',
        icon: 'science',
        type: 'PRIMARY'
      }

    ];

  }

}
