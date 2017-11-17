import {Injectable, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../model/user';
import {GiftComService} from '../services/gift-com.service';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class AuthService implements OnInit {

  userChange = new Subject<User>();
  user: User;
  loggedIn = false;

  defaultPassword = 'password';

  constructor(private router: Router, private giftComService: GiftComService) {

  }

  ngOnInit() {
    console.log('in Auth On Init')
    if (this.loggedIn === false && localStorage.getItem('loggedIn') === 'true') {
      this.giftComService.getCurrentUser().subscribe((user: User) => {
          this.loggedIn = true;
          this.user = user;
          this.userChange.next(user);
        },
        error => {
          console.log(error);
        }
      );
    }
  }

}
