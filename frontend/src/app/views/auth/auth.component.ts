import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {AuthService} from './auth.service';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  public loginForm: boolean = true;
  public username: string = '';
  public password: string = '';
  public newUsername: string = '';
  public newPassword: string = '';
  public confirmNewPassword: string = '';
  public loginFields = [
    {
      label: 'Username:',
      type: 'text',
      placeholder: 'Your username',
      onChange: (event: Event) => this.username = (event.target as HTMLInputElement).value
    }, {
      label: 'Password:',
      type: 'password',
      placeholder: 'Your password',
      onChange: (event: Event) => this.password = (event.target as HTMLInputElement).value
    }
  ];
  public signupFields = [
    {
      label: 'Username:',
      type: 'text',
      placeholder: 'Your username',
      onChange: (event: Event) => this.newUsername = (event.target as HTMLInputElement).value
    }, {
      label: 'Password:',
      type: 'password',
      placeholder: 'Your password',
      onChange: (event: Event) => this.newPassword = (event.target as HTMLInputElement).value
    }, {
      label: 'Confirm Password:',
      type: 'password',
      placeholder: 'Confirm your password',
      onChange: (event: Event) => this.confirmNewPassword = (event.target as HTMLInputElement).value
    }
  ];

  constructor(private authService: AuthService, private router: Router) {}

  public login(event: MouseEvent): void {
    if (this.username.length !== 0 && this.password.length !== 0) {
      this.authService.login({
        username: this.username,
        password: this.password
      })
        .subscribe(
          (data: any) => {
            localStorage.setItem('token', data.token);
            this.router.navigate(['']);
          },
          (error: HttpErrorResponse) => console.error(error)        
        );      
    }
  }

  public signup(event: MouseEvent): void {
    if (
      this.newUsername.length !== 0
      && this.newPassword.length !== 0
      && this.newPassword === this.confirmNewPassword
    ) {
      this.authService.signup({
        username: this.newUsername,
        password: this.newPassword
      })
        .subscribe(
          (data: any) => {
            localStorage.setItem('token', data.token);
            this.router.navigate(['']);
          },
          (error: HttpErrorResponse) => console.error(error)        
        );      
    }
  }

  public swapForm(event: MouseEvent): void {
    this.loginForm = !this.loginForm;
  }

  public onSwapForm(): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.swapForm(event);
    }
  }

  public onLogin(): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.login(event);
    }
  }

  public onSignup(): (event: MouseEvent) => void {
    return (event: MouseEvent): void => {
      this.signup(event);
    }
  }
}
