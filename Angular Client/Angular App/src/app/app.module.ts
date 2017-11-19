import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {ShoppingListService} from './shopping-list/shopping-list.service';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppRoutingModule} from "./app-routing.module";
import {RecipeService} from './recipes/recipe.service';
import {DataStorageService} from './shared/data-storage.service';
import {AuthService} from './auth/auth.service';
import {AuthGuard} from './auth/auth.guard';
import {SharedModule} from "./shared/shared.module";
import {ShoppingListModule} from "./shopping-list/shopping-list.module";
import {AuthModule} from "./auth/auth.module";
import {HomeComponent} from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ShoppingListModule,
    AuthModule,
    SharedModule
  ],
  providers: [ShoppingListService, RecipeService, DataStorageService, AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
