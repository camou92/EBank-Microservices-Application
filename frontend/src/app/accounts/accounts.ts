import { AsyncPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { Account, AccountListState, RequestStatus } from '../model/account.model';
import { LoadingService } from '../services/loading';

@Component({
  selector: 'app-accounts',
  imports: [AsyncPipe],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts {
  RequestStatus = RequestStatus;
  private http = inject(HttpClient);
  public loadService = inject(LoadingService)
  account$ : Observable<AccountListState> = this.http.get<Account[]>('http://localhost:9999/EBANK-SERVICE/account')
  .pipe(
    map(resp => {
      return {accounts:resp, status:RequestStatus.SUCCESS}
    }),
    catchError((err, caught) => {
      return of({status: RequestStatus.ERROR, errorMessage: err.statusText })
    })
  );
}
