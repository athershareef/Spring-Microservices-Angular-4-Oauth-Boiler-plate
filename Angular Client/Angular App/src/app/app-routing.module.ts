import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SigninComponent} from './auth/signin/signin.component';
import {GoogleSigninComponent} from './auth/signin/google-signin/google-signin.component';
import {HomeComponent} from './home/home.component';
import {SignupComponent} from './auth/signup/signup.component';
import {AuthGuard} from './auth/auth.guard';
import {UserEditComponent} from './auth/user-edit/user-edit.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {ErrorComponent} from './error/error.component';
import {ItemsComponent} from './items/items.component';


const appRoutes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: SigninComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'login/google', component: GoogleSigninComponent},
  {path: 'login/google/success', component: HomeComponent},
  {path: 'forgotpassword', component: ForgotPasswordComponent},
  {path: 'user/edit', component: UserEditComponent, canActivate: [AuthGuard]},
  {path: 'item', component: ItemsComponent, canActivate: [AuthGuard]},
  // {path: '', redirectTo: '/vehicles', pathMatch: 'full'},
  // {
  //   path: 'vehicles', component: VehiclesComponent, children: [
  //   {path: '', component: VehicleStartComponent},
  //   {path: 'new', component: VehicleEditComponent},
  //   {path: ':id', component: VehicleDetailComponent},
  //   {path: ':id/edit', component: VehicleEditComponent},n
  // ]
  // },
  {path: '**', component: ErrorComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
