import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import {Observable, catchError, throwError, retry} from 'rxjs';
import IAuth from './auth';
import config from '../../../config.json';


@Injectable()
export class AuthService {			
  private httpOptions = { 
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    })
  }

  private authHttpOptions = { 
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    }) 
  }

  constructor(private httpClient: HttpClient) {}

  signup(data: IAuth): Observable<any> {
    return this.httpClient.post(config.url + '/api/signup', data, this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )
  }

  login(data: IAuth): Observable<any> {
    return this.httpClient.post(config.url + '/api/login', data, this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )
  }

  logout(): Observable<any> {
    return this.httpClient.post(config.url + '/api/logout', {}, this.authHttpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )
  }
}
