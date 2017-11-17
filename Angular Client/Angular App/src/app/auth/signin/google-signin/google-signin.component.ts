import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../auth.service';

declare const gapi: any;

@Component({
  selector: 'app-google-signin',
  templateUrl: './google-signin.component.html',
  styleUrls: ['./google-signin.component.css']
})
export class GoogleSigninComponent implements OnInit {


  constructor(private authService: AuthService) {
    gapi.load('auth2', function () {
      gapi.auth2.init();
    });
  }

  ngOnInit() {
  }

  googleSignIn() {
    const googleAuth = gapi.auth2.getAuthInstance();
    googleAuth.then(() => {
      googleAuth.signIn({scope: 'profile email'}).then(googleUser => {
        this.authService.signInGoogleUser(googleUser.getAuthResponse().id_token);
      });
    });
  }

  googleSignUp() {
    const googleAuth = gapi.auth2.getAuthInstance();
    googleAuth.then(() => {
      googleAuth.signIn({scope: 'profile email'}).then(googleUser => {
        this.authService.signUpGoogleUser(googleUser.getAuthResponse().id_token);
      });
    });
  }
}
