import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {DropdownDirective} from './misc/dropdown.directive';
import {AppRoutingModule} from './app-routing.module';
import {AuthComponent} from './auth/auth.component';
import {SigninComponent} from './auth/signin/signin.component';
import {SignupComponent} from './auth/signup/signup.component';
import {GoogleSigninComponent} from './auth/signin/google-signin/google-signin.component';
import {GiftComService} from './services/gift-com.service';
import {HttpClientModule} from '@angular/common/http';
import {AuthService} from './auth/auth.service';
import {HomeComponent} from './home/home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthGuard} from "./auth/auth.guard";
import {UserEditComponent} from './auth/user-edit/user-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DropdownDirective,
    AuthComponent,
    SigninComponent,
    SignupComponent,
    GoogleSigninComponent,
    HomeComponent,
    UserEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [GiftComService, AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
