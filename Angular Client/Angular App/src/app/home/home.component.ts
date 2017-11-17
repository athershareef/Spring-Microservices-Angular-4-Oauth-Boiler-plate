import {Component, OnDestroy, OnInit} from '@angular/core';
import {GiftComService} from '../services/gift-com.service';
import {AuthService} from '../auth/auth.service';
import {User} from '../model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  user: User;

  loggedIn = false;


  constructor(private giftComService: GiftComService, private authService: AuthService) {
    console.log('in Home Constructor' + this.loggedIn);

    if (this.loggedIn === false && localStorage.getItem('loggedIn') === 'true') {
      this.giftComService.getCurrentUser().subscribe((user: User) => {
          this.loggedIn = true;
          this.user = user;
          this.authService.user = this.user;
          this.authService.userChange.next(user);
        },
        error => {
          console.log(error);
          this.loggedIn = false;
          this.authService.user = new User('', '', '');
          this.authService.userChange.next(new User('', '', ''));
        }
      );
    }
  }

  ngOnInit() {
    if (this.loggedIn === false && localStorage.getItem('loggedIn') === 'true') {
      this.giftComService.getCurrentUser().subscribe((user: User) => {
          this.loggedIn = true;
          this.user = user;
          this.authService.user = this.user;
          this.authService.userChange.next(user);
        },
        error => {
          console.log(error);
          this.loggedIn = false;
          this.authService.user = new User('', '', '');
          this.authService.userChange.next(new User('', '', ''));
        }
      );
    }
  }

  ngOnDestroy(): void {
    console.log('in Home Destroy' + this.loggedIn);
    // this.userChangeSubs.unsubscribe();
  }
}
