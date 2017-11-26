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
import {UserComService} from './services/user-com.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthService} from './auth/auth.service';
import {HomeComponent} from './home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthGuard} from './auth/auth.guard';
import {UserEditComponent} from './auth/user-edit/user-edit.component';
import { ForgotPasswordComponent } from './auth/forgot-password/forgot-password.component';
import { ErrorComponent } from './error/error.component';
import {RequestInterceptorService} from './services/request-interceptor.service';
import { AlertComponent } from './alert/alert.component';
import {AlertService} from './services/alert.service';
import { ItemsComponent } from './items/items.component';
import { ItemEditComponent } from './items/item-edit/item-edit.component';
import { ItemDetailsComponent } from './items/item-details/item-details.component';
import { ItemListComponent } from './items/item-list/item-list.component';
import { ItemComponent } from './items/item-list/item/item.component';
import {ItemService} from "./items/item.service";

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
    ForgotPasswordComponent,
    ErrorComponent,
    AlertComponent,
    ItemsComponent,
    ItemEditComponent,
    ItemDetailsComponent,
    ItemListComponent,
    ItemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserComService, AuthService, AuthGuard,ItemService, AlertService, {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptorService,
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
