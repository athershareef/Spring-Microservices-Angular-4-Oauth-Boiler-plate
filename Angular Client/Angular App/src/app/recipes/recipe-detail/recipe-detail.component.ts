import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";

import {Recipe} from '../recipe.model';
import {RecipeService} from '../recipe.service';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
  id: number;
  recipeSelected: Recipe;

  constructor(private recipeService: RecipeService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.recipeSelected = this.recipeService.getRecipeByID(this.id);
      }
    );
  }


  addToShoppingList() {
    this.recipeService.addIngredientsToShoppingList(this.recipeSelected.ingredients);
  }

  deleteRecipe() {
    this.recipeService.deleteRecipe(this.id);
    this.router.navigate(['/recipes']);
  }
}
