import {Injectable, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user';
import {GiftComService} from '../services/gift-com.service';
import {Subject} from 'rxjs/Subject';

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

  constructor(private router: Router, private giftComService: GiftComService) {

  }

  ngOnInit() {
    console.log('in Auth On Init')
    if (this._loggedIn === false && localStorage.getItem('_loggedIn') === 'true') {
      this.giftComService.getCurrentUser().subscribe((user: User) => {
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
    this.giftComService.signInUser(user).subscribe(
      (response: User) => {
        this._loggedIn = true;
        this._user = response as User;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
        this.userChange.next(this._user);
      },
      error => {
        console.log(error);
      }
    );

  }

  signOutUser() {
    this.giftComService.signOut().subscribe(
      (response: any) => {
        console.log(response)
        this._user = new User('', '', '');
        localStorage.setItem('_loggedIn', 'false');
        this.router.navigate(['/login']);
        this.userChange.next(new User('', '', ''));
      },
      error => {
        console.log(error);
      }
    );
  }

  signUpUser(user: User) {
    this.giftComService.signUpUser(user).subscribe(
      (response: Response) => {
        console.log(response);
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
      }
    );
  }

  signInGoogleUser(token: String) {
    this.giftComService.validateGoogleOAuth(token).subscribe(
      (user: User) => {
        this.userChange.next(user);
        this._loggedIn = true;
        this._user = user;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
      },
      error => {
        console.log(error);
      }
    );
  }

  signUpGoogleUser(token: String) {
    this.giftComService.signUpGoogleUser(token).subscribe(
      (user: User) => {
        this._user = user;
        this._loggedIn = true;
        localStorage.setItem('_loggedIn', 'true');
        this.router.navigate(['/home']);
        this.userChange.next(user);
      },
      error => {
        console.log(error);
      }
    );
  }

  getCurrentUser() {
    this.giftComService.getCurrentUser().subscribe((user: User) => {
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

  updateUser(user: User) {
    this._user = user;
    this.giftComService.updateUser(user.userId, user).subscribe((response: any) => {
        this.userChange.next(user);
        this._user = user;
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }
}
