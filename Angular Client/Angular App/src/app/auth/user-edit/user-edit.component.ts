import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../auth.service';
import {User} from '../../model/user';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit, OnDestroy {
  userForm: FormGroup;
  user: User;
  loggedIn = false;
  userChangeSubs: Subscription;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.user = this.authService.user;

    this.userChangeSubs = this.authService.userChange.subscribe((user: User) => {
      console.log('Edit change fired');
      if (user.username !== '') {
        this.user = user;
        this.loggedIn = true;
      }
      if (user.email === '') {
        this.loggedIn = false;
      }
    });
    this.userForm = new FormGroup({
      'username': new FormControl(this.user.username, Validators.required),
      'email': new FormControl(this.user.email, Validators.required),
      'password': new FormControl(this.user.password, [Validators.required]),
      // 'password': new FormControl(this.user.password, [Validators.required, Validators.minLength(5)]),
    });
  }


  onSubmit() {
    this.user = new User(this.userForm.value.username, this.userForm.value.email, this.userForm.value.password, this.user.userId);
    this.authService.updateUser(this.user);
  }

  ngOnDestroy(): void {
    this.userChangeSubs.unsubscribe();
  }


}
