import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, catchError, retry, throwError} from 'rxjs';
import INote from './note';
import config from '../../../config.json';


@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private httpOptions = { 
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    }) 
  }

  constructor(private httpClient: HttpClient) {}

  readNotes(): Observable<INote[]> {
    return this.httpClient.get<INote[]>(config.url + '/api/notes', this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )  
  }

  createNote(note: INote): Observable<INote> {
    return this.httpClient.post<INote>(config.url + '/api/notes', {
      title: note.title,
      body: note.body
    }, this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )  
  }

  updateNote(note: INote): Observable<INote> {
    return this.httpClient.put<INote>(config.url + `/api/notes/${note.id}`, {
      title: note.title,
      body: note.body
    }, this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )  
  }

  deleteNote(id: number): Observable<any> {
    return this.httpClient.delete<any>(config.url + `/api/notes/${id}`, this.httpOptions)
      .pipe(
        retry(5),
        catchError((error: HttpErrorResponse) => throwError(error))
      )  
  }
}
