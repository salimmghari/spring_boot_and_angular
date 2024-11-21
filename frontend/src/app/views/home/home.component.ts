import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import INote from '../../components/note/note';
import {NoteService} from '../../components/note/note.service';
import {AuthService} from '../auth/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public notes: INote[] = [];
  public newNote: INote = {
    id: 0,
    title: '',
    body: ''
  };

  constructor(private noteService: NoteService, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.noteService.readNotes()
      .subscribe(
        (data: INote[]) => this.notes = data,
        (error: HttpErrorResponse) => console.error(error)
      )  
  }

  public createNote(event: MouseEvent): void {
    if (this.newNote.title.length !== 0 && this.newNote.body.length !== 0) {
      this.noteService.createNote(this.newNote)
      .subscribe(
        (data: INote) => this.ngOnInit(),
        (error: HttpErrorResponse) => console.error(error)        
      );
    }
  }

  public updateNote(event: MouseEvent, note: INote): void {
    if (note.title.length !== 0 || note.body.length !== 0) {
      this.noteService.updateNote(note)
      .subscribe(
        (data: INote) => this.ngOnInit(),
        (error: HttpErrorResponse) => console.error(error)        
      )  
    }
  }

  public deleteNote(event: MouseEvent, id: number): void {
    this.noteService.deleteNote(id)
      .subscribe(
        (data: any) => this.ngOnInit(),
        (error: HttpErrorResponse) => console.error(error)        
      )  
  }

  public logout(event: MouseEvent): void {
    this.authService.logout()
      .subscribe(
        (data: any) => {
          localStorage.removeItem('token');
          this.router.navigate(['auth']);
        },
        (error: HttpErrorResponse) => console.error(error)        
      );      
  }

  public onTitleChange(id: number): (event: Event) => void {
    return (event: Event): void => {
      let note: INote = this.newNote;
      if (id > 0) note = this.notes.filter((note: INote) => note.id === id)[0];
      note.title = (event.target as HTMLInputElement).value;
    }
  } 

  public onBodyChange(id: number): (event: Event) => void {
    return (event: Event): void => {
      let note: INote = this.newNote;
      if (id > 0) note = this.notes.filter((note: INote) => note.id === id)[0];
      note.body = (event.target as HTMLTextAreaElement).value;
    }
  } 

  public onCreate(): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.createNote(event);
    } 
  }

  public onUpdate(id: number): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      const note: INote = this.notes.filter((note: INote) => note.id === id)[0];
      this.updateNote(event, note);
    }
  } 

  public onDelete(id: number): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.deleteNote(event, id);
    }
  } 

  public onLogout(): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.logout(event);
    } 
  }
}
