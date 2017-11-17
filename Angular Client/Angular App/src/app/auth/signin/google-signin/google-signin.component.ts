import {Component, OnInit} from '@angular/core';
import {User} from '../../../model/user';
import {GiftComService} from '../../../services/gift-com.service';
import {AuthService} from '../../auth.service';
import {Router} from '@angular/router';

declare const gapi: any;

@Component({
  selector: 'app-google-signin',
  templateUrl: './google-signin.component.html',
  styleUrls: ['./google-signin.component.css']
})
export class GoogleSigninComponent implements OnInit {
  private user: User;

  constructor(private comService: GiftComService, private authService: AuthService, private router: Router) {
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
        this.comService.validateGoogleOAuth(googleUser.getAuthResponse().id_token).subscribe(
          (user: User) => {
            this.user = user;
            this.authService.user = this.user;
            this.authService.userChange.next(user);
            localStorage.setItem('loggedIn', 'true');
            this.router.navigate(['/home']);
          },
          error => {
            console.log(error);
          }
        );
      });
    });
  }

  googleSignUp() {
    const googleAuth = gapi.auth2.getAuthInstance();
    googleAuth.then(() => {
      googleAuth.signIn({scope: 'profile email'}).then(googleUser => {
        this.comService.signUpGoogleUser(googleUser.getAuthResponse().id_token).subscribe(
          (user: User) => {
            this.user = user;
            this.authService.user = this.user;
            this.authService.userChange.next(user);
            localStorage.setItem('loggedIn', 'true');
            this.router.navigate(['/home']);
          },
          error => {
            console.log(error);
          }
        );
      });
    });
  }
}
