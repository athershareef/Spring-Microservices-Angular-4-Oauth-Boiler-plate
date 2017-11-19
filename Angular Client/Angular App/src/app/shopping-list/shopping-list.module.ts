import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ShoppingEditComponent} from "./shopping-edit/shopping-edit.component";
import {ShoppingListComponent} from "./shopping-list.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [
    ShoppingListComponent,
    ShoppingEditComponent
  ]
})
export class ShoppingListModule {
}
