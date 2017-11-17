import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from "../../model/user";
import {GiftComService} from '../../services/gift-com.service';
import {Router} from "@angular/router";
import {AuthService} from "../auth.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit, OnDestroy {
  private loggedIn = false;
  userChangeSubs: Subscription;
  user: User;

  ngOnDestroy(): void {
    this.userChangeSubs.unsubscribe();
  }

  constructor(private giftComService: GiftComService, private router: Router, private authService: AuthService) {
    if (this.loggedIn === false && localStorage.getItem('loggedIn') === 'true') {
      this.giftComService.getCurrentUser().subscribe((user: User) => {
          this.loggedIn = true;
          this.user = user;
          this.authService.user = this.user;
          this.authService.userChange.next(user);
          this.router.navigate(['/home']);
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
    console.log('in Sigin OnInit' + this.loggedIn)
    this.userChangeSubs = this.authService.userChange.subscribe((user: User) => {
      this.user = user;
      if (user.email !== '') {
        this.loggedIn = true;
      } else {
        this.loggedIn = false;
      }
    });


    if (this.loggedIn === true) {
      this.router.navigate(['/home']);
    }
  }

  onSignIn(form: NgForm) {
    const user = new User('', form.value.email, form.value.password);
    this.giftComService.signInUser(user).subscribe(
      (response: User) => {
        this.loggedIn = true;
        this.user = response as User;
        this.authService.user = this.user;
        this.authService.userChange.next(this.user);
        localStorage.setItem('loggedIn', 'true');
        this.router.navigate(['/home']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
