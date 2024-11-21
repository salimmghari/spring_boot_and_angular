import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {NoteService} from './components/note/note.service';
import {AuthService} from './views/auth/auth.service';
import {AuthGuard} from './views/auth/auth.guard';
import {HomeComponent} from './views/home/home.component';
import {AuthComponent} from './views/auth/auth.component';
import {ButtonComponent} from './components/button/button.component';
import {TitleComponent} from './components/title/title.component';
import {LinkComponent} from './components/link/link.component';
import {LayoutComponent} from './components/layout/layout.component';
import {FieldComponent} from './components/field/field.component';
import {NoteComponent} from './components/note/note.component';
import {FormComponent} from './components/form/form.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthComponent,
    ButtonComponent,
    TitleComponent,
    LinkComponent,
    LayoutComponent,
    FieldComponent,
    NoteComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    NoteService,
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
