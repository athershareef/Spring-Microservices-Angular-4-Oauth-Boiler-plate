import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from '../../model/user';
import {GiftComService} from '../../services/gift-com.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private giftComService: GiftComService, private router: Router) {
  }

  ngOnInit() {
  }

  onSignUp(form: NgForm) {
    const user = new User(form.value.username, form.value.email, form.value.password);
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
}
