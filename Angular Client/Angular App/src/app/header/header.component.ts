import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {User} from '../model/user';
import {GiftComService} from '../services/gift-com.service';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  user: User;
  loggedIn = false;
  userChangeSubs: Subscription;


  constructor(private authService: AuthService, private giftComService: GiftComService, private router: Router) {
  }

  ngOnInit() {
    console.log('in Header OnInit' + this.loggedIn)
    this.userChangeSubs = this.authService.userChange.subscribe((user: User) => {
      this.user = user;
      if (user.email === '') {
        this.loggedIn = false;
      } else {
        this.loggedIn = true;
      }
    });
    console.log('in Header OnInit later' + this.loggedIn)

  }

  onSignOut() {
    this.giftComService.signOut().subscribe(
      (response: any) => {
        this.authService.user = new User('', '', '');
        this.authService.userChange.next(new User('', '', ''));
        this.user = new User('', '', '');
        localStorage.setItem('loggedIn', 'false');
        this.loggedIn = false;
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnDestroy(): void {
    this.userChangeSubs.unsubscribe();
  }

}
