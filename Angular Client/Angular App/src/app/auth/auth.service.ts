import {Injectable, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user';
import {UserComService} from '../services/user-com.service';
import {Subject} from 'rxjs/Subject';
import {AlertService} from '../services/alert.service';

@Injectable()
export class AuthService implements OnInit {

  userChange = new Subject<User>();
  private _user: User;
  private _loggedIn = false;

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  get loggedIn(): boolean {
    return this._loggedIn;
  }

  set loggedIn(value: boolean) {
    this._loggedIn = value;
  }

  constructor(private router: Router, private userComService: UserComService, private alertService: AlertService) {
  }

  ngOnInit() {
    console.log('in Auth On Init');
    if (this._loggedIn === false && localStorage.getItem('_loggedIn') === 'true') {
      this.userComService.getCurrentUser().subscribe((user: User) => {
          this._loggedIn = true;
          this._user = user;
          this.userChange.next(user);
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  signInUser(user: User) {
    this.userComService.signInUser(user).subscribe(
      (response: User) => {
        this._loggedIn = true;
        this._user = response as User;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
        this.userChange.next(this._user);
      },
      error => {
        this.alertService.error('Unable to Login, please try again !');
      }
    );

  }

  signOutUser() {
    this.userComService.signOut().subscribe(
      () => {
        this._user = new User('', '', '');
        localStorage.setItem('_loggedIn', 'false');
        this.router.navigate(['/login']);
        this.userChange.next(new User('', '', ''));
        this.alertService.success('Logged Out Successfully !');
      },
      () => {
        this.alertService.error('Unable to Logout !');
      }
    );
  }

  signUpUser(user: User) {
    this.userComService.signUpUser(user).subscribe(
      () => {
        this.router.navigate(['/login']);
        this.alertService.success('Successfully signed up!');
      },
      () => {
        this.alertService.error('User with the email Id already Exists !');
      }
    );
  }

  signInGoogleUser(token: String) {
    this.userComService.validateGoogleOAuth(token).subscribe(
      (user: User) => {
        this.userChange.next(user);
        this._loggedIn = true;
        this._user = user;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
      },
      () => {
        this.alertService.error('User is not available, please Signup');
      }
    );
  }

  signUpGoogleUser(token: String) {
    this.userComService.signUpGoogleUser(token).subscribe(
      (user: User) => {
        this._user = user;
        this._loggedIn = true;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
        this.userChange.next(user);
      },
      () => {
        this.router.navigate(['/login']);
        this.alertService.error('User with the email Id already Exists !');
      }
    );
  }

  getCurrentUser() {
    this.userComService.getCurrentUser().subscribe((user: User) => {
        this._loggedIn = true;
        this._user = user;
        this.userChange.next(user);
        this.router.navigate(['/home']);
      },
      error => {
        console.log(error);
        this._loggedIn = false;
        this._user = new User('', '', '');
        this.userChange.next(new User('', '', ''));
      }
    );
  }

  forgotPassword(email: String) {
    this.userComService.forgotPassword(email).subscribe(() => {
        this.router.navigate(['/login']);
        this.alertService.success('New password is sent to your email ID');
      },
      () => {
        this.router.navigate(['/login']);
        this.alertService.error('User with the given email is not present, please signup !');
      }
    );
  }

  updateUser(user: User) {
    this._user = user;
    this.userComService.updateUser(user.userId, user).subscribe(() => {
        this.userChange.next(user);
        this._user = user;
        this.alertService.success('Your profile is updated !');
      },
      () => {
        this.alertService.success('unable to edit your profile');
      }
    );
  }
}
