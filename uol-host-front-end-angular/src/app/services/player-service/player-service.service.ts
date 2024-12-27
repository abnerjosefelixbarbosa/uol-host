import { Injectable } from '@angular/core';
import { PlayerRequest } from '../../models/dtos/player-request';
import { PlayerResponse } from '../../models/dtos/player-response';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { API } from '../../utils/api';

export interface IPlayerService {
  registerPlayer(request: PlayerRequest): Observable<PlayerResponse>;
  listPlayer(): Observable<PlayerResponse>;
}

@Injectable({
  providedIn: 'root'
})
export class PlayerService implements IPlayerService {
  private url: string = new API().development;
  private errorMessage: string = '';
  
  constructor(private httpClient: HttpClient) { }

  registerPlayer(request: PlayerRequest): Observable<PlayerResponse> {
    return this.httpClient.post<any>(`${this.url}/api/players/register-player`, request)
    .pipe(
      catchError((err) => {
        this.errorMessage = err.message;

        return throwError(() => new Error(this.errorMessage));
      })
    );    
  }

  listPlayer(): Observable<PlayerResponse> {
    throw new Error('Method not implemented.');
  }
}
