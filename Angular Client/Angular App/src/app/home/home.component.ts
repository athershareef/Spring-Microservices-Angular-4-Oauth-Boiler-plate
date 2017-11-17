import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {User} from '../model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loggedIn = false;
  user: User;


  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.user = this.authService.user;
    this.loggedIn = this.authService.loggedIn;
    if (this.loggedIn === false && localStorage.getItem('loggedIn') === 'true') {
      this.authService.getCurrentUser();
    }
  }
}
