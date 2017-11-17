import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../auth.service";
import {User} from "../../model/user";
import {GiftComService} from "../../services/gift-com.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  id: number;
  userForm: FormGroup;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService,
              private giftComService: GiftComService) {
  }

  ngOnInit() {
    this.user = this.authService.user;
    // this.editMode = params['id'] != null;
    this.initForm();
  }

  initForm() {
    this.userForm = new FormGroup({
      'username': new FormControl(this.user.username, Validators.required),
      'email': new FormControl(this.user.email, Validators.required),
      'password': new FormControl(this.user.password, [Validators.required, Validators.minLength(5)]),
    });
  }


  onSubmit() {
    console.log(this.user.userId);
    const user = new User(this.userForm.value.username, this.userForm.value.email, this.userForm.value.password, this.user.userId);
    this.giftComService.updateUser(this.user.userId, user).subscribe((response: any) => {
        this.authService.userChange.next(user);
        this.authService.user = user;
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }

}
