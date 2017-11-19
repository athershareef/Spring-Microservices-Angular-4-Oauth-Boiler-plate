import {NgModule} from '@angular/core';
import {RecipeEditComponent} from "./recipe-edit/recipe-edit.component";
import {RecipeStartComponent} from "./recipe-start/recipe-start.component";
import {RecipeItemComponent} from "./recipe-list/recipe-item/recipe-item.component";
import {RecipeListComponent} from "./recipe-list/recipe-list.component";
import {RecipesComponent} from "./recipes.component";
import {RecipeDetailComponent} from "./recipe-detail/recipe-detail.component";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RecipesRoutingModule} from "./recipes-routing.module";
import {SharedModule} from "../shared/shared.module";

@NgModule({
  declarations: [
    RecipeDetailComponent,
    RecipesComponent,
    RecipeListComponent,
    RecipeItemComponent,
    RecipeStartComponent,
    RecipeEditComponent
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RecipesRoutingModule,
    SharedModule
  ]
})
export class RecipesModule {
}
