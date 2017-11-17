import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SigninComponent} from './auth/signin/signin.component';
import {GoogleSigninComponent} from './auth/signin/google-signin/google-signin.component';
import {HomeComponent} from './home/home.component';
import {SignupComponent} from './auth/signup/signup.component';
import {AuthGuard} from "./auth/auth.guard";
import {UserEditComponent} from "./auth/user-edit/user-edit.component";


const appRoutes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: SigninComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'login/google', component: GoogleSigninComponent},
  {path: 'login/google/success', component: HomeComponent},
  {path: 'user/edit', component: UserEditComponent, canActivate: [AuthGuard]},
  // {path: '', redirectTo: '/vehicles', pathMatch: 'full'},
  // {
  //   path: 'vehicles', component: VehiclesComponent, children: [
  //   {path: '', component: VehicleStartComponent},
  //   {path: 'new', component: VehicleEditComponent},
  //   {path: ':id', component: VehicleDetailComponent},
  //   {path: ':id/edit', component: VehicleEditComponent},
  // ]
  // },
  {path: '**', redirectTo: ''}

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
