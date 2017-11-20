import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {User} from '../model/user';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  user: User;
  loggedIn = false;
  userChangeSubs: Subscription;


  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    console.log('in Header Init' + this.loggedIn);
    this.userChangeSubs = this.authService.userChange.subscribe((user: User) => {
      this.user = user;
      if (user.email === '') {
        this.loggedIn = false;
      } else {
        this.loggedIn = true;
      }
    });
    console.log('in Header Cons Init' + this.loggedIn);
  }

  onSignOut() {
    this.authService.signOutUser();
  }

  ngOnDestroy(): void {
    this.userChangeSubs.unsubscribe();
  }

}
