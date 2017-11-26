import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  passwordForm: FormGroup;

  emailSent = false;

  constructor(private authService: AuthService) {
  }


  ngOnInit() {
    this.passwordForm = new FormGroup({
      'email': new FormControl('', Validators.required)
    });
  }


  onSubmit() {
    this.emailSent = true;
    this.authService.forgotPassword(this.passwordForm.value.email);
  }

}
